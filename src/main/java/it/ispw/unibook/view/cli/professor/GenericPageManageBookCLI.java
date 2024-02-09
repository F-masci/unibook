package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.professor.ManageBookCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Questa View raccoglie le funzioni comuni alle View per stampare le liste dei libri e dei corsi.
 */
public abstract class GenericPageManageBookCLI extends GenericPageCLI {

    /**
     * Stampa a schermo la lista dei corsi associati all'utente attualmente loggato
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageBookCLI</i>
     * @throws SessionException Viene lanciata nel caso in cui la sessione corrente non sia stata trovata
     */
    protected void printSessionCoursesList(@NotNull ManageBookCLI controller) throws SessionException {
        // Viene istanziato il bean per contenere i corsi
        CoursesListBean bean = new CoursesListBean();
        // Vengono richiesti i corsi al controller grafico
        controller.retrieveCoursesBySession(bean);
        // Viene stampata la lista associata al bean
        printCoursesListBean(bean, "I TUOI CORSI");
    }

    /**
     * Stampa a schermo la lista dei libri associati a un corso
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageBookCLI</i>
     * @param courseCode Codice del corso di cui si vuole stampare la lista dei libri
     * @throws CourseException Viene lanciata nel caso in cui il corso non sia stato trovato
     */
    protected void printCourseBooksList(ManageBookCLI controller, int courseCode) throws CourseException {
        // Viene istanziato il bean per contenere i corsi
        BooksListBean bean = new BooksListBean(courseCode);
        // Vengono richiesti i libri al controller grafico
        controller.retrieveBooksByCourse(bean);
        // Viene stampata la lista associata al bean
        printCourseBooksListBean(bean, "LIBRI ASSOCAITI AL CORSO");
    }

}
