package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.utils.ConnectionUniJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDaoAppJDBC implements CourseDao {

    private Connection connection = null;

    public CourseDaoAppJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    @Override
    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO sellable_book(course, isbn, seller, price) VALUES(?, ?, ?, ?);")) {
            stm.setInt(1, course.getCode());
            stm.setString(2, sellableBook.getIsbn());
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

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO book(course, isbn, title) VALUES(?, ?, ?);")) {
            stm.setInt(1, course.getCode());
            stm.setString(2, book.getIsbn());
            stm.setString(3, book.getTitle());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeBookFromCourse(CourseEntity course, BookEntity book) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM book WHERE course = ? AND isbn = ?;")) {
            stm.setInt(1, course.getCode());
            stm.setString(2, book.getIsbn());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
