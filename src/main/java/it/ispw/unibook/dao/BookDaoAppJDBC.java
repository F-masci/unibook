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
        PreparedStatement stm = null;
        List<BookEntity> books = new ArrayList<>();

        try {
            stm = connection.prepareStatement("SELECT * FROM book WHERE course=?");
            stm.setInt(1, course.getCode());
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    BookEntity book = new BookEntity(
                            res.getString("ISBN"),
                            res.getString("title")
                    );
                    books.add(book);
                } while (res.next());
            }

        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return books;

    }

    @Override
    public void saveCourseBooks(CourseEntity course) {

        List<BookEntity> toAdd = course.getAddedBooks();
        List<BookEntity> toDel = course.getDeletedBooks();

        try (PreparedStatement addStm = connection.prepareStatement("INSERT INTO book(course, ISBN, title) VALUES(?, ?, ?);");
             PreparedStatement delStm = connection.prepareStatement("DELETE FROM book WHERE course = ? AND ISBN = ?;")) {

            connection.setAutoCommit(false);

            for(BookEntity b: toAdd) {
                addStm.setInt(1, course.getCode());
                addStm.setString(2, b.getISBN());
                addStm.setString(3, b.getTitle());
                addStm.execute();
            }

            for(BookEntity b: toDel) {
                delStm.setInt(1, course.getCode());
                delStm.setString(2, b.getISBN());
                delStm.execute();
            }

            connection.commit();

        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                Printer.error(exception);
                System.exit(-1);
            }

            Printer.error(e);
            System.exit(-1);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                Printer.error(exception);
                System.exit(-1);
            }
        }

    }
}
