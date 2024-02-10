package it.ispw.unibook.entity;

import java.util.Objects;

/**
 * Rappresenta il concetto di libro
 */
public class BookEntity {

    // Codice identificativo del libro.
    // Si intende l'ISBN-13
    private final String isbn;
    // Titolo del libro
    private final String title;
    // Codice del corso a cui appartiene
    private final int courseCode;

    /**
     * Costruttore di default dell'entità
     * @param isbn Codice identificativo del libro
     * @param title Titolo del libro
     * @param courseCode Codice del corso a cui è collegato
     */
    public BookEntity(String isbn, String title, int courseCode) {
        this.isbn = isbn;
        this.title = title;
        this.courseCode = courseCode;
    }
    /**
     * Costruttore alternativo per creare il libro con solo isbn e titolo
     * @param isbn Codice identificativo del libro
     * @param title Titolo del libro
     */
    public BookEntity(String isbn, String title) {
        this(isbn, title, 0);
    }
    /**
     * Costruttore alternativo per creare il libro con solo il codice
     * @param isbn Codice identificativo del libro
     */
    public BookEntity(String isbn) {
        this(isbn, null, 0);
    }

    /**
     *
     * @return ISBN del libro
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @return Titolo del libro
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return Codice del corso a cui appartiene il libro
     */
    public int getCourseCode() {
        return courseCode;
    }

    /**
     * Due libri risultano uguali se hanno lo stesso codice (ISBN).
     * @param o Oggetto da confrontare
     * @return <i>true</i> se gli oggetti sono uguali.
     *         <i>false</i> se gli oggetti sono diversi.
     */
    @Override
    public boolean equals(Object o) {
        // Se l'oggetto è quello corrente sono uguali
        if (this == o) return true;
        // Se l'oggetto è un'istanza diversa da BookEntity allora sono diversi
        if (!(o instanceof BookEntity)) return false;
        // Confronto i codici per capire se sono uguali
        return Objects.equals( ((BookEntity) o).getIsbn(), this.getIsbn() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getIsbn());
    }
}
