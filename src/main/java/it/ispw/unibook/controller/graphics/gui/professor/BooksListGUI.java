package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import it.ispw.unibook.utils.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksListGUI extends ManageBookGUI implements Initializable {

    // Facade per l'accesso al sottosistema di gestione dei corsi e dei libri
    ManageCourseBookFacade facade = new ManageCourseBookFacade();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private VBox booksList;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei corsi con i corsi associati all'utente loggato
        super.loadSessionCourses(coursesCombo);
    }

    @FXML
    public void onCourseSelected(ActionEvent event) throws CourseException {
        try {
            // Controlla che il corso selezionato sia cambiato
            int selected = super.getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            // In caso sia cambiato viene aggiornato il valore del corso correntemente selezionato
            courseSelected = selected;
            // Viene inizializzato il bean per contenere la lista dei libri relativi al corso
            BooksListBean bean = new BooksListBean(courseSelected);
            // Viene caricata la lista dei libri
            facade.retrieveBooksByCourse(bean);
            // Viene stampata la lista dei libri
            super.loadCourseBooksIntoList(booksList, bean);
        } catch (CourseException | ComboSelectionNotValidException e) { // Queste due eccezioni non dovrebbero essere mai sollevate poiché si utilizzano delle combo box con valori prefissati
            Printer.error(e);
            System.exit(-1);
        }
    }
}
