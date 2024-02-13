package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class HomeGUI extends GenericGUI {

    /**
     * Mostra la pagina con la lista dei corsi
     */
    @FXML
    public void showCourses() {
        changePage(PagesGUI.COURSES_LIST_STUDENT);
    }

    /**
     * Mostra la pagina con la lista dei libri per un corso
     */
    @FXML
    public void showCourseBooks() {
        changePage(PagesGUI.COURSE_BOOKS_LIST_STUDENT);
    }

    /**
     * Mostra la pagina con il menù per le trattative attive
     */
    @FXML
    public void showActiveNegotiationMenu() {
        changePage(PagesGUI.ACTIVE_NEGOTIATION_MENU_STUDENT);
    }

    /**
     * Mostra la pagina per iniziare l'acquisto di un libro
     */
    @FXML
    public void showPurchaseBookMenu() {
        changePage(PagesGUI.PURCHASE_BOOK_MENU_STUDENT);
    }

    /**
     * Mostra la pagina per inserire un libro in vendita
     */
    @FXML
    public void insertSellableBook() {
        changePage(PagesGUI.INSERT_SELLABLE_BOOK_STUDENT);
    }

    /**
     * Mostra la pagina per rimuovere un libro in vendita
     */
    @FXML
    public void removeSellableBook() {
        changePage(PagesGUI.REMOVE_SELLABLE_BOOK_STUDENT);
    }

    /**
     * Mostra la pagina per impostare un libro come venduto
     */
    @FXML
    public void markSellableBookSold() {
        changePage(PagesGUI.MARK_SELLABLE_BOOK_SOLD_STUDENT);
    }

}
