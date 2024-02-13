package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveBookGUI extends ManageBookGUI implements Initializable {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro rimosso correttamente";

    // Facade per l'accesso al sottosistema di gestione dei corsi e dei libri
    private final ManageCourseBookFacade facade = new ManageCourseBookFacade();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> booksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML Button removeBookButton;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei corsi con i corsi associati all'utente loggato
        super.loadSessionCourses(coursesCombo);
    }

    @FXML
    public void loadBooksList(ActionEvent event) {
        try {
            // Controlla che il corso selezionato sia cambiato
            int selected = super.getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            // In caso sia cambiato viene aggiornato il valore del corso correntemente selezionato
            courseSelected = selected;
            // Viene caricata la combo dei libri collegati al corso
            super.loadCourseBooks(booksCombo, selected);
        } catch (ComboSelectionNotValidException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void removeBook() {
        // Viene resettato il messaggio di errore
        errorLabel.setText("");
        // Contienel'il libro selezionato
        String isbn = null;

        try {
            // Carica il libro selezionato dalla combo
            isbn = super.getCourseBookSelectedFromComboBox(booksCombo);
        } catch (ComboSelectionNotValidException e) {
            errorLabel.setText(e.getMessage());
            return;
        }

        try {
            // Viene istanziato il bean contenete il corso selezionato
            CourseBean courseBean = new CourseBean(courseSelected);
            // Viene istanziato il bean contenete il libro selezionato
            BookBean bookBean = new BookBean(isbn);
            // Rimuove il libro dal corso svolgendo il caso d'uso
            facade.removeBookFromCourse(courseBean, bookBean);

            // Vengono oscurati i pulsanti per eseguire le azioni e disabilitati i campi per l'inserimento
            coursesCombo.setDisable(true);
            booksCombo.setDisable(true);
            removeBookButton.setVisible(false);
            // Viene impostato il messaggio di conferma dell'operazione
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch (BookException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
