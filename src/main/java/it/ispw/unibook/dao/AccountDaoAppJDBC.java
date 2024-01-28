package it.ispw.unibook.dao;

import it.ispw.unibook.entity.*;
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
    public AccountEntity retrieveAccountByCode(int code) {
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
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return account;
    }

    @Override
    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
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

    @Override
    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO negotiation(book, student) VALUES(?, ?);")) {
            stm.setInt(1, sellableBook.getCode());
            stm.setInt(2, buyer.getCode());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM negotiation WHERE book=? AND student=?;")) {
            stm.setInt(1, sellableBook.getCode());
            stm.setInt(2, buyer.getCode());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer) {
        try (PreparedStatement stm = connection.prepareStatement("UPDATE sellable_book SET buyer=? WHERE code=?;")) {
            stm.setInt(1, buyer.getCode());
            stm.setInt(2, sellableBook.getCode());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
