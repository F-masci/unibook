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

public class NegotiationDaoAppJDBC implements NegotiationDao {

    private Connection connection = null;

    public NegotiationDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
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

}
