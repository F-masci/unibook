package it.ispw.unibook.controller.graphics.gui;

import javafx.fxml.FXML;

public class GenericGUI {

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }

    protected void changePage(PagesGUI page) {
        ControllerGUI.setPage(page);
    }

}
