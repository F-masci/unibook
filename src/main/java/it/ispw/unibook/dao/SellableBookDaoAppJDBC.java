package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellableBookDaoAppJDBC implements SellableBookDao {

    // Unica istanza di DAO dei libri in vendita che sfrutta JDBC
    private static SellableBookDaoAppJDBC instance = null;

    // Connessione al database del sistema
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private SellableBookDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di DAO dei libri in vendita che sfrutta JDBC
     * @return DAO dei libri in vendita che utilizza JDBC
     */
    public static SellableBookDaoAppJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new SellableBookDaoAppJDBC();
        return instance;
    }

    @Override
    public SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE code=? LIMIT 1;")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, code);
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                // Sfrutta la funzione ausiliare per creare l'entità a partire dal Resul Set
                return createEntityFromViewResultSet(res);
            } else {
                // Se non ci sono risultati viene sollevata l'eccezione
                throw new SellableBookNotFoundException();
            }
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return null;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE isbn=?;")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setString(1, isbn);
            // Viene eseguita la query e il Result Set viene gestito dalla funzione ausiliare
            // Vengono aggiunte le varie entità all'interno della lista da restituire
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Vine ritornata la lista
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity account) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE sellerCode=?;")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, account.getCode());
            // Viene eseguita la query e il Result Set viene gestito dalla funzione ausiliare
            // Vengono aggiunte le varie entità all'interno della lista da restituire
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Vine ritornata la lista
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_negotiation WHERE studentCode=?;")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, negotiationBuyer.getCode());
            // Viene eseguita la query e il Result Set viene gestito dalla funzione ausiliare
            // Vengono aggiunte le varie entità all'interno della lista da restituire
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Vine ritornata la lista
        return sellableBooks;

    }

    @Override
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_sellable_book WHERE course=?;")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, course.getCode());
            // Viene eseguita la query e il Result Set viene gestito dalla funzione ausiliare
            // Vengono aggiunte le varie entità all'interno della lista da restituire
            addResultSetToList(stm.executeQuery(), sellableBooks);
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Vine ritornata la lista
        return sellableBooks;
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

    @Override
    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("INSERT INTO negotiation(book, student) VALUES(?, ?);")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setInt(1, sellableBook.getCode());
            stm.setInt(2, buyer.getCode());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM negotiation WHERE book=? AND student=?;")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setInt(1, sellableBook.getCode());
            stm.setInt(2, buyer.getCode());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer) {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("UPDATE sellable_book SET buyer=? WHERE code=?;")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setInt(1, buyer.getCode());
            stm.setInt(2, sellableBook.getCode());
            // Viene eseguita la QUERY
            stm.execute();
        } catch(SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Funzione ausiliare per istanziare una entity a partire dal Result Set della vista del database
     * @param res Result set della vista
     * @return Entità con i dati presenti nel result set
     * @throws SQLException Viene sollevata se ci sono errori durante la lettura del result set
     */
    private SellableBookEntity createEntityFromViewResultSet(ResultSet res) throws SQLException {
        // Viene istanziato il dao per ottenere gli account del venditore e dell'eventuale acquirente effettivo
        AccountDao accountDao = ApplicationDaoFactory.getInstance().getAccountDao();
        // Viene richiesto l'account del venditore al DAO
        AccountEntity seller = null;
        try {
            seller = accountDao.retrieveAccountByCode(res.getInt("sellerCode"));
        } catch (AccountNotFoundException ignored) {
            // Quest'eccezione non viene mai sollevata poiché l'account deve esistere necessariamente nella persistenza
        }
        // Viene istanziato l'acquirente effettivo
        AccountEntity buyer = null;
        // Viene estratto l'eventuale codice dell'acquirente effettivo
        int buyerCode = res.getInt("buyerCode");
        // Se il codice estratto è diverso da null viene cercato sulla persistenza
        if(!res.wasNull()) {
            try {
                buyer = accountDao.retrieveAccountByCode(buyerCode);
            } catch (AccountNotFoundException ignored) {
                // Quest'eccezione non viene mai sollevata poiché l'account deve esistere necessariamente nella persistenza
            }
        }
        // Viene creata l'entità a partire dal Result Set
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

    /**
     * Funzione ausiliare per creare la lista di entità a partire dal Result Set
     * @param res Result Set da cui estrarre le informazioni
     * @param sellableBooks Lista delle entità
     * @throws SQLException Viene sollevata se ci sono errori durante la lettura del result set
     */
    private void addResultSetToList(ResultSet res, List<SellableBookEntity> sellableBooks) throws SQLException {
        if(res.first()) {
            do {
                // Sfrutta la funzione ausiliare per istanziare l'entità a partire dal Result Set e lo aggiunge alla lista
                sellableBooks.add(createEntityFromViewResultSet(res));
            } while (res.next());
        }
    }

}
