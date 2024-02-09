package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.MarkSellableBookSoldController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MarksSellableBookSoldGUI extends ManageSellableBookGUI implements Initializable {

    private final MarkSellableBookSoldController controller = new MarkSellableBookSoldController();

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private ComboBox<String> buyersCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button markSellableBookSoldButton;

    private int sellableBookSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSessionSellableBooks(sellableBooksCombo);
    }

    @FXML
    public void onSellableBookSelected(ActionEvent event) {
        try {
            int selected = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            if (selected == sellableBookSelected) return;
            sellableBookSelected = selected;
            loadSellableBookBuyers(buyersCombo, selected);
            buyersCombo.setDisable(false);
        } catch(ComboSelectionNotValidException | SellableBookException | BookException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void markSellableBookSold() {
        errorLabel.setText("");

        try {
            int buyer = super.getAccountSelectedFromComboBox(buyersCombo);
            SellableBookBean sellableBookBean = new SellableBookBean(sellableBookSelected);
            AccountBean buyerBean = new AccountBean(buyer);
            controller.markSellableBookSold(sellableBookBean, buyerBean);
            sellableBooksCombo.setDisable(true);
            buyersCombo.setDisable(true);
            markSellableBookSoldButton.setVisible(false);
            successLabel.setText("Libro impostato correttamente come venduto");
        } catch (BookException | ComboSelectionNotValidException | SellableBookException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
