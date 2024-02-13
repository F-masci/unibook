package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class HomeGUI extends GenericGUI {

    /**
     * Mostra la pagina con la lista dei libri per un corso
     */
    @FXML
    public void showCourseBooks() {
        changePage(PagesGUI.COURSE_BOOKS_LIST_PROFESSOR);
    }

    /**
     * Mostra la pagina di inserimento di un libro a un corso
     */
    @FXML
    public void insertCourseBook() {
        changePage(PagesGUI.INSERT_COURSE_BOOK_PROFESSOR);
    }

    /**
     * Mostra la pagina di rimozioni di un libro a un corso
     */
    @FXML
    public void removeCourseBook() {
        changePage(PagesGUI.REMOVE_COURSE_BOOK_PROFESSOR);
    }

}
