package it.ispw.unibook.dao;

import it.ispw.unibook.entity.*;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoAppJDBC implements AccountDao {

    private Connection connection = null;

    public AccountDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    @Override
    public AccountEntity retrieveAccountByCode(int code) throws AccountNotFoundException {
        AccountEntity account = null;
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM account WHERE code=?")){
            stm.setInt(1, code);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                account = new AccountEntity(
                        res.getInt("code"),
                        res.getString("email"),
                        AccountTypes.getFromString(res.getString("type")),
                        res.getString("name"),
                        res.getString("surname")
                );
            } else {
                throw new AccountNotFoundException();
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return account;
    }

    @Override
    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook) {

        List<AccountEntity> buyers = new ArrayList<>();

        try(PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_negotiation WHERE book=?")) {
            stm.setInt(1, sellableBook.getCode());
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    AccountEntity buyer = new AccountEntity(
                            res.getInt("studentCode"),
                            res.getString("studentEmail"),
                            AccountTypes.getFromString("Studente"),
                            res.getString("studentName"),
                            res.getString("studentSurname")
                    );
                    buyers.add(buyer);
                } while (res.next());
            }
            res.close();
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return buyers;

    }

}
