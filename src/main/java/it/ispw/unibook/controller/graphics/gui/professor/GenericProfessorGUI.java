package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public abstract class GenericProfessorGUI extends GenericGUI {
    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }
}
