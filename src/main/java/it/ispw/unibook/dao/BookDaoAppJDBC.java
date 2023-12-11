package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.awt.print.Book;
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
    public List<BookEntity> getCourseBooks(int courseCode) {

        // TODO: controllare se bisogna applicare il pattern FACTORY
        PreparedStatement stm = null;
        List<BookEntity> booksList = new ArrayList<>();

        try {
            stm = connection.prepareStatement("SELECT * FROM book WHERE course=?");
            stm.setInt(1, courseCode);
            ResultSet res = stm.executeQuery();

            if(res.first()) {
                do {
                    BookEntity book = new BookEntity(
                            res.getString("ISBN"),
                            res.getString("title")
                    );
                    booksList.add(book);
                } while (res.next());
            }

        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return booksList;

    }

}
