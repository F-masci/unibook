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

    // Unica istanza di DAO dei corsi che sfrutta JDBC
    private static CourseDaoAppJDBC instance = null;

    // Connessione al database del sistema
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private CourseDaoAppJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di DAO dei corsi che sfrutta JDBC
     * @return DAO dei corsi che utilizza JDBC
     */
    public static CourseDaoAppJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new CourseDaoAppJDBC();
        return instance;
    }

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO book(course, isbn, title) VALUES(?, ?, ?);")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setInt(1, course.getCode());
            stm.setString(2, book.getIsbn());
            stm.setString(3, book.getTitle());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeBookFromCourse(CourseEntity course, BookEntity book) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM book WHERE course = ? AND isbn = ?;")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setInt(1, course.getCode());
            stm.setString(2, book.getIsbn());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO sellable_book(course, isbn, seller, price) VALUES(?, ?, ?, ?);")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setInt(1, course.getCode());
            stm.setString(2, sellableBook.getIsbn());
            stm.setInt(3, sellableBook.getSeller().getCode());
            stm.setFloat(4, sellableBook.getPrice());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM sellable_book WHERE code=?;")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, sellableBook.getCode());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
