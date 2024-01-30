package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
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
    protected void printCoursesList(@NotNull ManageBookCLI controller) throws SessionException {
        // Viene istanziato il bean per contenere i corsi
        CoursesListBean bean = new CoursesListBean();
        // Vengono richiesti i corsi al controller grafico
        controller.retrieveCoursesBySession(bean);
        // Viene estratta la lista dei corsi
        List<CourseBean> courses = bean.getList();

        Printer.println("\n--- I TUOI CORSI ---");

        // Vengono stampati i corsi utilizzando il metodo <i>toString</i> del bean
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c);
        }
        Printer.println("");
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
        // Viene estratta la lista dei corsi
        List<BookBean> books = bean.getList();

        Printer.println("\n--- LIBRI COLLEGATI AL CORSO ---");

        // Vengono stampati i corsi utilizzando il metodo <i>toString</i> del bean
        for (BookBean b : books) {
            Printer.println("[" + b.getISBN() + "] " + b);
        }

        Printer.println("");

    }

    /**
     * Richiede all'utente di inserire un codice di un libro (ISBN)
     * @return ISBN inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected String requestBookCode() throws EscCliException {
        return requestBookCode("ISBN del libro (oppure esc per uscire): ");
    }

    /**
     * Richiede all'utente di inserire un codice di un libro (ISBN)
     * @param msg Messaggio da stampare per la richiesta del codice
     * @return ISBN inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected String requestBookCode(String msg) throws EscCliException {
        // Richiama la funzione più generale per richiedere una stringa
        return requestString(msg);
    }

    /**
     * Richiede all'utente di inserire un codice di un corso
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestCourseCode() throws EscCliException {
        return requestCourseCode("Codice del corso (oppure esc per uscire): ");
    }
    /**
     * Richiede all'utente di inserire un codice di un corso
     * @param msg Messaggio da stampare per la richiesta del codice
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestCourseCode(String msg) throws EscCliException {
        // Richiama la funzione più generale per richiedere un intero
        return requestInt(msg);
    }

}
