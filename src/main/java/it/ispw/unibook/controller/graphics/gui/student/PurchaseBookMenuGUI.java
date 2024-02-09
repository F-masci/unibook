package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class PurchaseBookMenuGUI extends GenericGUI {

    @FXML
    public void showGlobalSearch() {
        changePage(PagesGUI.GLOBAL_SEARCH_STUDENT);
    }

    @FXML
    public void showSearchByCourse() {
        changePage(PagesGUI.SEARCH_BY_COURSE_STUDENT);
    }

}
