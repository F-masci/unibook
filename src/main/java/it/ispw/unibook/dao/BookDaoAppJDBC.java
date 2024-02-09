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

    private static BookDaoAppJDBC instance = null;
    private Connection connection = null;

    public static BookDaoAppJDBC getInstance() {
        if(instance == null) instance = new BookDaoAppJDBC();
        return instance;
    }

    private BookDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    @Override
    public List<BookEntity> retrieveCourseBooks(CourseEntity course) {

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

}
