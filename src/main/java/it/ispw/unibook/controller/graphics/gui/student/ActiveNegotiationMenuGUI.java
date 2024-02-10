package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class ActiveNegotiationMenuGUI extends ManageSellableBookGUI {

    @FXML
    public void showOwnSellableBooks() {
        changePage(PagesGUI.OWN_SELLABLE_BOOKS_LIST_STUDENT);
    }

    @FXML
    public void showActiveNegotiationList() {
        changePage(PagesGUI.ACTIVE_NEGOTIATION_LIST_STUDENT);
    }

}
