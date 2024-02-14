package it.ispw.unibook.facade;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.controller.application.InsertCourseBookController;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import org.jetbrains.annotations.NotNull;

/**
 * Applicazione del pattern <i>façade</i> per fornire un accesso unico al sottosistema per il reperimento
 * dei dati dei corsi e dei libri associati.<br>
 * La classe espone i metodi per gestire sulla persistenza i corsi e i libri a essi associati
 * necessari alle View
 */
public class ManageCourseBookFacade {

    // Controller per ottenere le informazioni dei corsi
    private final CourseController courseController = new CourseController();
    // Controller per ottenere l'informazione dei libri
    private final BookController bookController = new BookController();
    // Controller per eseguire il caso d'uso <i>Inserisci libro in un corso</i>
    private final InsertCourseBookController insertCourseBookController = new InsertCourseBookController();
    // Controller per eseguire il caso d'uso <i>Rimuovi libro da un corso</i>
    private final RemoveCourseBookController removeCourseBookController = new RemoveCourseBookController();

    /**
     * Ritorna la lista dei corsi associati all'utente loggato
     * @param bean Deve contenere il <i>sessionId</i>.
     *             Contiene la lista dei corsi
     * @throws SessionException Viene sollevata se la sessione non viene trovata
     */
    public void retrieveCoursesBySession(@NotNull CoursesListBean bean) throws SessionException {
        courseController.retrieveCoursesBySession(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public void retrieveBooksByCourse(@NotNull BooksListBean bean) throws CourseException {
        bookController.retrieveBooksByCourse(bean);
    }

    /*-- INSERIMENTO --*/

    /**
     * Cerca il libro nella libreria tramite ISBN
     * @param bean Deve contenere l'ISBN del libro da cercare.
     *             Contiene il dati del libro trovato
     * @throws BookException Viene sollevata se il libro non è stato trovato
     */
    public void getBookInformation(@NotNull BookBean bean) throws BookException {
        insertCourseBookController.getBookInformation(bean);
    }

    /**
     * Inserisce il libro al corso completando il caso d'uso
     * @param courseBean Deve contenere il codice del corso in cui inserire il libro
     * @param bookBean Deve contenere tutti i dati del libro da inserire
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void insertBookInCourse(@NotNull CourseBean courseBean, @NotNull BookBean bookBean) throws CourseException {
        insertCourseBookController.insertBookInCourse(courseBean, bookBean);
    }

    /*-- RIMOZIONE --*/

    /**
     * Rimuove il libro dal corso completando il caso d'uso
     * @param courseBean Deve contenere il codice del corso in cui inserire il libro
     * @param bookBean Deve contenere il codice del libro da rimuovere
     * @throws BookException Viene sollevata se il libro non è presente nel corso
     */
    public void removeBookFromCourse(@NotNull CourseBean courseBean, @NotNull BookBean bookBean) throws BookException, CourseException {
        removeCourseBookController.removeBookFromCourse(courseBean, bookBean);
    }

}
