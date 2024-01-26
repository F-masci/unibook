package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;

import java.util.List;

/**
 * Interfaccia da implementare per i dao che interagiscono con la persistenza per le entità Corso
 */
public interface CourseDao {

    public CourseEntity retrieveCourseByCode(int code);
    public List<CourseEntity> retrieveCoursesByProfessor(AccountEntity professor);
    public CourseEntity retrieveCourseBySellableBook(SellableBookEntity sellableBook, AccountEntity seller);
    public List<CourseEntity> retrieveCourses();

}
