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

}
