package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;

/**
 * Questo controller grafico implementa tutte quelle funzioni che permettono alle View di ottenere i dati
 * da mostrare all'utente che sono richiesti da più classi. I Controller grafici che gestiscono queste View
 * dovrebbero essere implementati come sottoclasse di questo Controller generale.
 */
public abstract class ManageBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei corsi e dei libri
    private final ManageCourseBookFacade facade = new ManageCourseBookFacade();

    /**
     * Ritorna la lista dei corsi associati all'utente loggato
     * @param bean Deve contenere il <i>sessionId</i>.
     *             Contiene la lista dei corsi
     * @throws SessionException Viene sollevata se la sessione non viene trovata
     */
    public void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        facade.retrieveCoursesBySession(bean);
    }
    /**
     * Ritorna la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi
     */
    public void retrieveCourses(CoursesListBean bean) {
        facade.retrieveCourses(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        facade.retrieveBooksByCourse(bean);
    }

}
