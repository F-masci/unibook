package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertSellableBookGUI extends ManageSellableBookGUI implements Initializable {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro inserito correttamente in vendita";

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> booksCombo;

    @FXML
    private TextField priceField;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button insertSellableBookButton;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei corsi con tutti i corsi del sistema
        super.loadAllCourses(coursesCombo);
    }

    @FXML
    public void onCourseSelected(ActionEvent event) {
        try {
            // Controlla che il corso selezionato sia cambiato
            int selected = super.getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            // In caso sia cambiato viene aggiornato il valore del corso correntemente selezionato
            courseSelected = selected;
            // Viene caricata la combo con i libri del corso selezionato
            super.loadCourseBooks(booksCombo, selected);

            booksCombo.setDisable(false);
        } catch(ComboSelectionNotValidException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void insertSellableBook() {
        // Viene resettato il messaggio di errore
        errorLabel.setText("");

        try {
            // Viene istanziato il bean con i dati del libro da inserire in vendita
            String isbn = super.getCourseBookSelectedFromComboBox(booksCombo);
            Float price = Float.valueOf(priceField.getText());
            SellableBookBean bean = new SellableBookBean(courseSelected, isbn, price);
            // Viene inserito il libro in vendita
            facade.insertSellableBook(bean);

            coursesCombo.setDisable(true);
            booksCombo.setDisable(true);
            priceField.setDisable(true);
            insertSellableBookButton.setVisible(false);
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch (ComboSelectionNotValidException | SellableBookException | SessionException | CourseException | NumberFormatException | FieldNotValidException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
