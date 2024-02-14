package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.AccountTypes;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellableBookDaoAppJDBC implements SellableBookDao {

    private static SellableBookDaoAppJDBC instance = null;

    private Connection connection = null;

    private SellableBookDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    public static SellableBookDaoAppJDBC getInstance() {
        if(instance == null) instance = new SellableBookDaoAppJDBC();
        return instance;
    }

    @Override
    public SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException {

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE code=? LIMIT 1;")) {
            stm.setInt(1, code);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                return createEntityFromViewResultSet(res);
            }

            throw new SellableBookNotFoundException();

        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return null;

    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn) {

        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE isbn=?;")) {
            stm.setString(1, isbn);
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity account) {

        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE sellerCode=?;")) {
            stm.setInt(1, account.getCode());
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer) {

        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_negotiation WHERE studentCode=?;")) {
            stm.setInt(1, negotiationBuyer.getCode());
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return sellableBooks;

    }

    @Override
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course) {

        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE course=?;")) {
            stm.setInt(1, course.getCode());
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return sellableBooks;
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

    private SellableBookEntity createEntityFromViewResultSet(ResultSet res) throws SQLException {
            AccountEntity seller = new AccountEntity(
                    res.getInt("sellerCode"),
                    res.getString("sellerEmail"),
                    AccountTypes.STUDENT,
                    res.getString("sellerName"),
                    res.getString("sellerSurname")
            );
            int buyerCode = res.getInt("buyerCode");
            AccountEntity buyer = null;
            if(!res.wasNull()) {
                buyer = new AccountEntity(
                        buyerCode,
                        res.getString("buyerEmail"),
                        AccountTypes.STUDENT,
                        res.getString("buyerName"),
                        res.getString("buyerSurname")
                );
            }
            return new SellableBookEntity(
                    res.getInt("code"),
                    res.getString("isbn"),
                    res.getString("title"),
                    res.getFloat("price"),
                    seller,
                    buyer,
                    res.getInt("course")
            );
    }

    private void addResultSetToList(ResultSet res, List<SellableBookEntity> sellableBooks) throws SQLException {
        if(res.first()) {
            do {
                SellableBookEntity sellableBook = createEntityFromViewResultSet(res);
                sellableBooks.add(sellableBook);
            } while (res.next());
        }
    }

}
