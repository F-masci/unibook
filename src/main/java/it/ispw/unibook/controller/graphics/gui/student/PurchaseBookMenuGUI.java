package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class PurchaseBookMenuGUI extends ManageSellableBookGUI {

    /**
     * Mostra la pagina per cercare un libro nel sistema tramite ISBN
     */
    @FXML
    public void showGlobalSearch() {
        changePage(PagesGUI.GLOBAL_SEARCH_STUDENT);
    }

    /**
     * Mostra la pagina per cercare un libro in vendita in un corso
     */
    @FXML
    public void showSearchByCourse() {
        changePage(PagesGUI.SEARCH_BY_COURSE_STUDENT);
    }

}
