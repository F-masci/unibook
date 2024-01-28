package it.ispw.unibook.dao;

import it.ispw.unibook.entity.*;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
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

    private Connection connection = null;

    public SellableBookDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
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
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    SellableBookEntity sellableBook = createEntityFromViewResultSet(res);
                    sellableBooks.add(sellableBook);
                } while (res.next());
            }

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
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    SellableBookEntity sellableBook = createEntityFromViewResultSet(res);
                    sellableBooks.add(sellableBook);
                } while (res.next());
            }

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
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    SellableBookEntity sellableBook = createEntityFromViewResultSet(res);
                    sellableBooks.add(sellableBook);
                } while (res.next());
            }

        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return sellableBooks;
    }

    @Override
    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO sellable_book(course, isbn, seller, price) VALUES(?, ?, ?, ?);")) {
            stm.setInt(1, course.getCode());
            stm.setString(2, sellableBook.getISBN());
            stm.setInt(3, sellableBook.getSeller().getCode());
            stm.setFloat(4, sellableBook.getPrice());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM sellable_book WHERE code=?;")) {
            stm.setInt(1, sellableBook.getCode());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    // TODO: modificare con Facotry
    private SellableBookEntity createEntityFromViewResultSet(ResultSet res) throws SQLException {
            AccountEntity seller = new AccountEntity(
                    res.getInt("sellerCode"),
                    res.getString("sellerEmail"),
                    AccountTypes.STUDENT,
                    res.getString("sellerName"),
                    res.getString("sellerSurname")
            );
            SellableBookEntity sellableBook = new SellableBookEntity(
                    res.getInt("code"),
                    res.getString("isbn"),
                    res.getString("title"),
                    res.getFloat("price"),
                    seller
            );
            int buyerCode = res.getInt("buyerCode");
            if(!res.wasNull()) {
                AccountEntity buyer = new AccountEntity(
                        res.getInt("buyerCode"),
                        res.getString("buyerEmail"),
                        AccountTypes.STUDENT,
                        res.getString("buyerName"),
                        res.getString("buyerSurname")
                );
                try {
                    sellableBook.markAsSold(buyer);
                } catch (SellableBookAlreadySoldException ignored) {}
            }
            return sellableBook;
    }

}
