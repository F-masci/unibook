package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class ActiveNegotiationMenuGUI extends ManageSellableBookGUI {

    /**
     * Mostra la pagina con la lista delle trattative dei libri in vendita dell'utente loggato
     */
    @FXML
    public void showOwnSellableBooks() {
        changePage(PagesGUI.OWN_SELLABLE_BOOKS_LIST_STUDENT);
    }

    /**
     * Mostra la pagina con la lista delle trattative dell'utente loggato
     */
    @FXML
    public void showActiveNegotiationList() {
        changePage(PagesGUI.ACTIVE_NEGOTIATION_LIST_STUDENT);
    }

}
