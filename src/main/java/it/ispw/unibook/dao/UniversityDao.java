package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;

import java.util.List;

/**
 * Interfaccia da implementare per i dao che interagiscono con la persistenza per le entità Corso
 */
public interface UniversityDao {

    /**
     * Cerca il corso sullo strato di persistenza attraverso il codice
     * @param code Codice del corso
     * @return Corso con il codice fornito
     * @throws CourseNotFoundException Viene sollevata se il libro non è stato trovato
     */
    CourseEntity retrieveCourseByCode(int code) throws CourseNotFoundException;

    /**
     * Cerca i corsi sullo strato di persistenza attraverso il professore che lo insegna
     * @param professor Professore che tiene il corso
     * @return Lista dei corsi del professore
     */
    List<CourseEntity> retrieveCoursesByProfessor(AccountEntity professor);

    /**
     *
     * @return Tutti i corsi del sistema
     */
    List<CourseEntity> retrieveCourses();

}
