package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class HomeGUI extends GenericStudentGUI {

    @FXML
    public void showCourses() {
        changePage(PagesGUI.COURSES_LIST_STUDENT);
    }

    @FXML
    public void showCourseBooks() {
        changePage(PagesGUI.COURSE_BOOKS_LIST_STUDENT);
    }

    @FXML
    public void showActiveNegotiationMenu() {
        changePage(PagesGUI.ACTIVE_NEGOTIATION_MENU_STUDENT);
    }

    @FXML
    public void showPurchaseBookMenu() {
        changePage(PagesGUI.PURCHASE_BOOK_MENU_STUDENT);
    }

    @FXML
    public void insertSellableBook() {
        changePage(PagesGUI.INSERT_SELLABLE_BOOK_STUDENT);
    }

    @FXML
    public void removeSellableBook() {
        changePage(PagesGUI.REMOVE_SELLABLE_BOOK_STUDENT);
    }

    @FXML
    public void markSellableBookSold() {
        changePage(PagesGUI.MARK_SELLABLE_BOOK_SOLD_STUDENT);
    }

}
