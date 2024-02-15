package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class GlobalSearchGUI extends ManageSellableBookGUI {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Trattativa iniziata correttamente";

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    @FXML
    private TextField isbnField;

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button purchaseBookButton;
    @FXML
    private Button searchBookButton;

    private String isbnPre = null;

    @FXML
    public void searchBook() {
        errorLabel.setText("");
        try {
            // Controlla che il libro cercato sia cambiato
            String isbn = isbnField.getText();
            if (Objects.equals(isbn, isbnPre)) return;
            // In caso sia cambiato viene aggiornato il valore del libro correntemente cercato
            isbnPre = isbn;
            // Viene caricata la combo dei libri in vendita
            super.loadIsbnSellableBooks(sellableBooksCombo, isbn);

            sellableBooksCombo.setDisable(false);
            purchaseBookButton.setDisable(false);
        } catch (FieldNotValidException | SessionNotFoundException e) {
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setDisable(true);
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void purchaseBook() {
        errorLabel.setText("");
        try {
            // Viene istanziato il bean con il libro in vendita selezionato
            int selected = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            SellableBookBean bean = new SellableBookBean(selected);
            // Viene avviato l'acquisto del libro
            facade.purchaseBook(bean);

            isbnField.setDisable(true);
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setVisible(false);
            searchBookButton.setVisible(false);
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch (ComboSelectionNotValidException | SellableBookException | NegotiationException | SessionException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
