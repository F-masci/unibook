package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;

public interface CourseDao {

    /**
     * Aggiunge un libro a un corso
     * @param course Corso a cui aggiungere il libro
     * @param book Libro da aggiungere
     */
    void addBookToCourse(CourseEntity course, BookEntity book);

    /**
     * Rimuove un libro da un corso
     * @param course Corso da cui rimuovere il libro
     * @param book Libro da rimuovere
     */
    void removeBookFromCourse(CourseEntity course, BookEntity book);

    /**
     * Aggiunge un libro in vendita a un corso
     * @param course Corso a cui aggiungere il libro in vendita
     * @param sellableBook Libro in vendita da aggiungere
     */
    void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook);

    /**
     * Rimuove un libro in vendita da un corso
     * @param course Corso da cui rimuovere il libro in vendita
     * @param sellableBook Libro in vendita da rimuovere
     */
    void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook);

}
