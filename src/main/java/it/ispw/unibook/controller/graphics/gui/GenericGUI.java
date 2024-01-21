package it.ispw.unibook.controller.graphics.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GenericGUI {

    protected void changePage(PagesGUI page) {
        ControllerGUI.setPage(page);
    }

}
