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

    // Unica istanza di DAO dei libri che sfrutta JDBC
    private static BookDaoAppJDBC instance = null;

    // Connessione al database del sistema
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private BookDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di DAO dei libri che sfrutta JDBC
     * @return DAO dei libri che utilizza JDBC
     */
    public static BookDaoAppJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new BookDaoAppJDBC();
        return instance;
    }

    @Override
    public List<BookEntity> retrieveCourseBooks(CourseEntity course) {

        // Lista dei libri da restituire
        List<BookEntity> books = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try(PreparedStatement stm = connection.prepareStatement("SELECT * FROM book WHERE course=?")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, course.getCode());
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                do {
                    // Viene istanziata l'entità a partire dal Result Set
                    BookEntity book = new BookEntity(
                            res.getString("isbn"),
                            res.getString("title"),
                            course.getCode()
                    );
                    // L'entità creata viene aggiunta alla lista
                    books.add(book);
                } while (res.next());
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        // Viene ritornata la lista
        return books;

    }

}
