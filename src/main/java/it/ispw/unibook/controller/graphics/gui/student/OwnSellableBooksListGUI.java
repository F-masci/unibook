package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.AccountsListBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OwnSellableBooksListGUI extends ManageSellableBookGUI implements Initializable {

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private VBox buyersList;

    private int sellableBookSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Viene caricata la combo dei libri in vendita con i libri in vendita associati all'utente loggato
        super.loadSessionSellableBooks(sellableBooksCombo);
    }

    @FXML
    public void onSellableBookSelected(ActionEvent event) {
        try {
            // Controlla che il libro in vendita selezionato sia cambiato
            int selected = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            if (selected == sellableBookSelected) return;
            // In caso sia cambiato viene aggiornato il valore del libro in vendita correntemente selezionato
            sellableBookSelected = selected;

            // Viene istanziato il bean contenente il codice del libro in vendita selezionato
            SellableBookBean sellableBookBean = new SellableBookBean(selected);
            // Viene richiesto al padre il bean contenente gli acquirenti del libro in vendita selezionato
            AccountsListBean accountsBean = super.retrieveActiveNegotiationBySellableBook(sellableBookBean);
            // Viene estratta dal bean la lista degli acquirenti del libro in vendita
            List<AccountBean> buyers = accountsBean.getList();

            // Viene stampata la lista degli acquirenti
            buyersList.getChildren().clear();
            for (AccountBean a : buyers) {
                String text = a.getCode() + " - " + a;
                Label label = new Label(text);
                label.getStyleClass().add("buyer");
                buyersList.getChildren().add(label);
            }
        } catch (ComboSelectionNotValidException | SellableBookException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }
}
