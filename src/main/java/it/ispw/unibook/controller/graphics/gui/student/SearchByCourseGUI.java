package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import it.ispw.unibook.utils.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchByCourseGUI extends ManageSellableBookGUI implements Initializable {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Trattativa iniziata correttamente";

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML Button purchaseBookButton;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei corsi con tutti i corsi del sistema
        loadAllCourses(coursesCombo);
    }

    @FXML
    public void onCourseSelected(ActionEvent event) {
        try {
            // Controlla che il corso selezionato sia cambiato
            int selected = super.getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            // In caso sia cambiato viene aggiornato il valore del corso correntemente selezionato
            courseSelected = selected;
            // Viene caricata la combo con i libri in vendita del corso selezionato
            super.loadCourseSellableBooks(sellableBooksCombo, selected);

            sellableBooksCombo.setDisable(false);
            purchaseBookButton.setDisable(false);
        } catch (CourseException e) {
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setDisable(true);
            errorLabel.setText(e.getMessage());
        } catch(ComboSelectionNotValidException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @FXML
    public void purchaseBook() {
        try {
            // Viene istanziato il bean contenete il libro in vendita selezionato
            int selected = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            SellableBookBean bean = new SellableBookBean(selected);
            // Viene avviato l'acquisto del libro
            facade.purchaseBook(bean);

            coursesCombo.setDisable(true);
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setVisible(false);
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch (ComboSelectionNotValidException | SellableBookException | NegotiationException | SessionException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
