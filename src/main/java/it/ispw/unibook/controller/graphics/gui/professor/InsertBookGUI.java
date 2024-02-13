package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.ISBNNotValidException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertBookGUI extends ManageBookGUI implements Initializable {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro inserito correttamente";

    // Facade per l'accesso al sottosistema di gestione dei corsi e dei libri
    private final ManageCourseBookFacade facade = new ManageCourseBookFacade();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField titleField;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button searchBookButton;
    @FXML Button saveBookButton;

    @FXML
    private Button insertManualButton;
    @FXML
    private Button retryButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button errorButton;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei corsi con i corsi associati all'utente loggato
        super.loadSessionCourses(coursesCombo);
    }

    @FXML
    public void onCourseSelected(ActionEvent event) {
        try {
            // Controlla che il corso selezionato sia cambiato
            int selected = super.getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            // In caso sia cambiato viene aggiornato il valore del corso correntemente selezionato
            courseSelected = selected;
            // Viene disabilitato il TextField che permette l'inserimento dell'ISBN
            isbnField.setDisable(false);
        } catch (ComboSelectionNotValidException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void getBookInformation() {
        // Vengono resettati i campi del titolo e il messaggio di errore
        titleField.setText("");
        errorLabel.setText("");

        try {
            // Viene istanziato il bean contenente il libro da salvare
            BookBean bean = new BookBean(isbnField.getText());
            // Vengono visualizzati i pulsanti per eseguire le azioni
            searchBookButton.setVisible(false);
            isbnField.setDisable(true);
            // Viene cercato il libro tramite l'ISBN inserito
            facade.getBookInformation(bean);

            // Il libro è stato trovato e il nome viene visualizzato nell'apposito campo
            titleField.setText(bean.getName());
            // Vengono visualizzati i pulsanti per eseguire le azioni
            confirmButton.setVisible(true);
            errorButton.setVisible(true);
        } catch (BookException e) {
            // Imposta il messaggio di errore
            errorLabel.setText(e.getMessage());
            // Ottiene la causa che ha scatenato l'errore
            Throwable cause = e.getCause();
            if(cause.getClass() == ISBNNotValidException.class) {               // ISBN non valido
                searchBookButton.setVisible(true);
            } else if(cause.getClass() == BookNotFoundException.class) {        // Libro non trovato
                retryButton.setVisible(true);
                insertManualButton.setVisible(true);
            }
        }
    }

    @FXML
    public void retryInsertAuto() {
        // Vengono resettati i campi dell'ISBN, del titolo e il messaggio di errore
        errorLabel.setText("");
        isbnField.setText("");
        titleField.setText("");
        // Vengono visualizzati i pulsanti per eseguire le azioni
        retryButton.setVisible(false);
        insertManualButton.setVisible(false);
        searchBookButton.setVisible(true);
        coursesCombo.setDisable(false);
        isbnField.setDisable(false);
    }

    @FXML
    public void manualInsert() {
        // Viene resettato il messaggio di errore
        errorLabel.setText("");
        // Vengono visualizzati i pulsanti per eseguire le azioni
        retryButton.setVisible(false);
        insertManualButton.setVisible(false);
        titleField.setDisable(false);
        isbnField.setDisable(false);
        saveBookButton.setVisible(true);
    }

    @FXML
    public void errorBook() {
        // Vengono visualizzati i pulsanti per eseguire le azioni
        confirmButton.setVisible(false);
        errorButton.setVisible(false);
        retryButton.setVisible(true);
        insertManualButton.setVisible(true);
    }

    @FXML
    public void confirmBook() {
        // Viene resettato il messaggio di errore
        errorLabel.setText("");

        // Vengono oscurati i pulsanti per eseguire le azioni e disabilitati i campi per l'inserimento
        confirmButton.setVisible(false);
        errorButton.setVisible(false);
        saveBookButton.setVisible(false);
        coursesCombo.setDisable(true);
        isbnField.setDisable(true);
        titleField.setDisable(true);

        try {
            // Viene istanziato il bean contenete il corso selezionato
            CourseBean courseBean = new CourseBean(courseSelected);
            // Viene istanziato il bean contenente i dati del libro da inserire
            BookBean bookBean = new BookBean(isbnField.getText(), titleField.getText());
            // Inserisce il libro al corso svolgendo il caso d'uso
            facade.insertBookInCourse(courseBean, bookBean);
            // Viene impostato il messaggio di conferma dell'operazione
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch(FieldNotValidException e) {
            errorLabel.setText(e.getMessage());
            coursesCombo.setDisable(false);
            isbnField.setDisable(false);
            titleField.setDisable(false);
            saveBookButton.setVisible(true);
        } catch (CourseException e) {
            errorLabel.setText(e.getMessage());
            retryButton.setVisible(true);
            insertManualButton.setVisible(true);
        }


    }

}
