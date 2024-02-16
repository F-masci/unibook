package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;

import java.util.List;

public interface BookDao {

    /**
     * Cerca la lista dei libri di un corso
     * @param course Corso
     * @return Lista dei libri del corso
     */
    List<BookEntity> retrieveCourseBooks(CourseEntity course);

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

}
