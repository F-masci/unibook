package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.PurchaseBookController;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class GlobalSearchGUI extends ManageSellableBookGUI {

    private final PurchaseBookController controller = new PurchaseBookController();

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
        try {
            String isbn = isbnField.getText();
            if (Objects.equals(isbn, isbnPre)) return;
            isbnPre = isbn;
            loadIsbnSellableBooks(sellableBooksCombo, isbn);
            sellableBooksCombo.setDisable(false);
            purchaseBookButton.setDisable(false);
        } catch (FieldNotValidException e) {
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setDisable(true);
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void purchaseBook() {
        errorLabel.setText("");
        try {
            int selected = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            SellableBookBean bean = new SellableBookBean(selected);
            controller.purchaseBook(bean);
            isbnField.setDisable(true);
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setVisible(false);
            searchBookButton.setVisible(false);
            successLabel.setText("Trattativa iniziata correttamente");
        } catch (ComboSelectionNotValidException | SellableBookException | NegotiationException | SessionException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
