package it.ispw.unibook.controller.grafici.gui;

import javafx.fxml.FXML;

public class LoginControllerGUI {

    final ControllerGUI gui = ControllerGUI.getInstance();

    @FXML
    public void accedi() {
        gui.impostaPagina(PagineGUI.HOME_PROFESSORE);
    }
}
