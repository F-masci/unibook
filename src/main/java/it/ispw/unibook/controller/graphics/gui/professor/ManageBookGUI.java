package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import it.ispw.unibook.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public abstract class ManageBookGUI extends GenericGUI {

    // Funzione utilizzata dalle pagine per impostare il pulsante per tornare alla home
    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }

    // Facade per l'accesso al sottosistema di gestione dei corsi e dei libri
    private final ManageCourseBookFacade manageCourseBookFacade = new ManageCourseBookFacade();

    /**
     * Carica nella combo i corsi collegati all'utente loggato
     * @param combo ComboBox su cui caricare l'elenco dei corsi
     */
    protected void loadSessionCourses(ComboBox<String> combo) {
        try {
            // Viene istanziato il bean per contenere i corsi
            CoursesListBean bean = new CoursesListBean();
            // Vengono caricati i corsi nel bean
            manageCourseBookFacade.retrieveCoursesBySession(bean);
            // Si utilizza la funzione generale per caricare i corsi in una ComboBox
            loadCoursesComboBox(combo, bean);
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Carica nella combo i libri collegati al corso
     * @param combo ComboBox su cui caricare l'elenco dei corsi
     * @param course Corso selezionato
     * @throws CourseException Viene sollevata nel caso in cui il corso non sia stato trovato
     */
    protected void loadCourseBooks(ComboBox<String> combo, int course) throws CourseException {
        // Viene istanziato il bean per contenere i libri del corso
        BooksListBean bean = new BooksListBean(course);
        // Vengono caricati i libri nel bean
        manageCourseBookFacade.retrieveBooksByCourse(bean);
        // Si utilizza la funzione generale per caricare i libri in una ComboBox
        loadCourseBooksComboBox(combo, bean);
    }

}
