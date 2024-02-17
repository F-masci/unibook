package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveSellableBookGUI extends ManageSellableBookGUI implements Initializable {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro in vendita rimosso correttamente";

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button removeSellableBookButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei libri in vendita con i libri in vendita associati all'utente loggato
        super.loadSessionSellableBooks(sellableBooksCombo);
    }

    @FXML
    public void removeSellableBook() {
        // Viene resettato il messaggio di errore
        errorLabel.setText("");

        try {
            // Viene istanziato il bean con il codice del libro da rimuovere
            int code = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            SellableBookBean bean = new SellableBookBean(code);
            // Viene rimosso il libro in vendita
            facade.removeSellableBook(bean);

            removeSellableBookButton.setVisible(false);
            sellableBooksCombo.setDisable(true);
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch (ComboSelectionNotValidException | SellableBookException | SessionException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
