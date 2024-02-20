package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
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

public class MarksSellableBookSoldGUI extends ManageSellableBookGUI implements Initializable {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro impostato correttamente come venduto";
    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

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
            // Viene caricata la combo con gli acquirenti del libro in vendita selezionato
            super.loadSellableBookBuyers(buyersCombo, selected);

            buyersCombo.setDisable(false);
        } catch(ComboSelectionNotValidException | SellableBookException e) {
            errorLabel.setText(e.getMessage());
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @FXML
    public void markSellableBookSold() {
        // Viene resettato il messaggio di errore
        errorLabel.setText("");

        try {
            // Viene istanziato il bean con il codice del libro in vendita e l'acquirente che lo ha effettivamente comprato
            int buyer = super.getAccountSelectedFromComboBox(buyersCombo);
            SellableBookBean sellableBookBean = new SellableBookBean(sellableBookSelected);
            AccountBean buyerBean = new AccountBean(buyer);
            // Viene impostato l'acquirente reale del libro in vendita
            facade.markSellableBookSold(sellableBookBean, buyerBean);

            sellableBooksCombo.setDisable(true);
            buyersCombo.setDisable(true);
            markSellableBookSoldButton.setVisible(false);
            successLabel.setText(SUCCESS_MESSAGE_TEXT);
        } catch (ComboSelectionNotValidException | SellableBookException | AccountNotFoundException e) {
            errorLabel.setText(e.getMessage());
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
