package it.ispw.unibook.entity;

import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.UnsellableBookInCourseException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Rappresenta il concetto di corso
 */
public class CourseEntity {

    // Codice del corso
    private final int code;
    // Nome del corso
    private final String name;
    // Anno accademico di inizio del corso
    private final int startYear;
    // Anno accademico di fine del corso
    private final int endYear;
    // Libri associati al corso
    private List<BookEntity> books = null;
    // Libri in vendita associati al corso
    private List<SellableBookEntity> sellableBooks = null;

    /**
     * Costruttore di default per l'entità
     * @param code Codice del corso
     * @param name Nome del corso
     * @param startYear Anno accademico di inizio del corso
     * @param endYear Anno accademico di fine del corso
     */
    public CourseEntity(int code, String name, int startYear, int endYear) {
        this.code = code;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Costruttore alternativo per l'entità utilizzando solo il codice del corso
     * @param code Codice del corso
     */
    public CourseEntity(int code) {
        this(code, null, 0, 0);
    }

    /**
     *
     * @return Codice del corso
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @return Nome del corso
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Anno accademico di inizio del corso
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     *
     * @return Anno accademico di fine del corso
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     *
     * @return Lista dei libri associati al corso
     */
    public List<BookEntity> getBooks() {
        loadBooks();
        return books;
    }

    /**
     *
     * @return Libri in vendita associati al corso
     */
    public List<SellableBookEntity> getSellableBooks() {
        loadSellableBooks();
        return sellableBooks;
    }

    /**
     * Aggiunge un libro al corso
     * @param book Libro da aggiungere
     * @throws BookAlreadyInCourseException Viene sollevata se il corso e già presente nel corso
     */
    public void addBook(@NotNull BookEntity book) throws BookAlreadyInCourseException {
        // Viene caricata la lista dei libri del corso
        loadBooks();
        // Viene controllato se il libro è già presente nel corso
        if(books.contains(book)) throw new BookAlreadyInCourseException();
        // Aggiunge il libro alla lista
        books.add(book);
        // Salva in persistenza l'aggiunta del libro
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        dao.addBookToCourse(this, book);
    }

    /**
     * Rimuove un libro dal corso
     * @param book Libro da rimuovere
     * @throws BookNotInCourseException Viene sollevata se il libro no è presente nel corso
     */
    public void removeBook(@NotNull BookEntity book) throws BookNotInCourseException {
        // Viene caricata la lista dei libri del corso
        loadBooks();
        // Viene controllato se il libro è effettivamente presente nel corso
        if(!books.contains(book)) throw new BookNotInCourseException();
        // Rimuove i libri in vendita collegati
        clearSellableBooksByIsbn(book.getIsbn());
        // Rimuove il libro dalla lista
        books.remove(book);
        // Salva in persistenza la rimozione del libro
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        dao.removeBookFromCourse(this, book);
    }

    /**
     * Aggiunge un libro in vendita al corso
     * @param sellableBook Libro in vendita da aggiungere
     * @throws UnsellableBookInCourseException Viene sollevata se il libro non può essere venduto nel corso
     */
    public void addSellableBook(@NotNull SellableBookEntity sellableBook) throws UnsellableBookInCourseException {
        // Viene caricata la lista dei libri in vendita del corso
        loadSellableBooks();
        // Viene caricata la lista dei libri del corso
        loadBooks();
        // Se il libro che si intende vendere non è un'istanza di un libro presente nel corso viene sollevata un'eccezione
        if(!books.contains(sellableBook)) throw new UnsellableBookInCourseException();
        // Aggiunge il libro in vendita al corso
        sellableBooks.add(sellableBook);
        // Salva in persistenza l'aggiunta del libro in vendita
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.addSellableBookToCourse(this, sellableBook);
    }

    /**
     * Rimuove un libro in vendita dal corso
     * @param sellableBook Libro in vendita da rimuovere
     * @throws SellableBookNotFoundException Viene sollevata se il libro non è presente tra quelli in vendita del corso
     */
    public void removeSellableBook(@NotNull SellableBookEntity sellableBook) throws SellableBookNotFoundException {
        // Viene caricata la lista dei libri in vendita del corso
        loadSellableBooks();
        // Viene controllato se il libro in vendita è effettivamente presente nel corso
        if(!sellableBooks.contains(sellableBook)) throw new SellableBookNotFoundException();
        // Vengono rimossi tutti gli acquirenti del libro
        sellableBook.clearBuyers();
        // Rimuove il libro in vendita dalla lista
        sellableBooks.remove(sellableBook);
        // Salva in persistenza la rimozione del libro in vendita
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.removeSellableBookFromCourse(this, sellableBook);
    }

    /**
     * Carica, se non presente, la lista dei libri collegati al corso
     */
    private void loadBooks() {
        if(books == null) {
            BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
            books = dao.retrieveCourseBooks(this);
        }
    }

    /**
     * Carica, se non presente, la lista dei libri in vendita collegati al corso
     */
    private void loadSellableBooks() {
        if(sellableBooks == null) {
            SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
            sellableBooks = dao.retrieveCourseSellableBooks(this);
        }
    }

    /**
     * Elimina i libri in vendita nel corso con uno specifico ISBN
     */
    private void clearSellableBooksByIsbn(String isbn) {
        // Crea un copia locale della lista dei libri in vendita
        List<SellableBookEntity> sellableBookCopy = new ArrayList<>(this.getSellableBooks());
        // Scorre la lista e rimuove il libro in vendita sulla lista dell'entità
        for(SellableBookEntity s: sellableBookCopy) {
            try {
                if(Objects.equals(s.getIsbn(), isbn)) this.removeSellableBook(s);
            } catch(SellableBookNotFoundException ignored) {
                // Quest'eccezione non viene mai sollevata
            }
        }
    }

    /**
     * Due corsi risultano uguali se hanno lo stesso codice.
     * @param o Oggetto da confrontare
     * @return <i>true</i> se gli oggetti sono uguali.
     *         <i>false</i> se gli oggetti sono diversi.
     */
    @Override
    public boolean equals(Object o) {
        // Se l'oggetto è quello corrente sono uguali
        if (this == o) return true;
        // Se l'oggetto è un'istanza diversa da CourseEntity allora sono diversi
        if (!(o instanceof CourseEntity)) return false;
        // Confronto i codici per capire se sono uguali
        return Objects.equals( ((CourseEntity) o).getCode(), this.getCode() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode());
    }

}
