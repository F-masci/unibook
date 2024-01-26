package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoAppJDBC implements BookDao {

    private Connection connection = null;

    public BookDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    @Override
    public List<BookEntity> retrieveCourseBooks(CourseEntity course) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        List<BookEntity> books = new ArrayList<>();

        try(PreparedStatement stm = connection.prepareStatement("SELECT * FROM book WHERE course=?")) {
            stm.setInt(1, course.getCode());
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    BookEntity book = new BookEntity(
                            res.getString("isbn"),
                            res.getString("title")
                    );
                    books.add(book);
                } while (res.next());
            }
            res.close();
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return books;

    }

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO book(course, isbn, title) VALUES(?, ?, ?);")) {
            stm.setInt(1, course.getCode());
            stm.setString(2, book.getISBN());
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
            stm.setString(2, book.getISBN());
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
