package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;

public class HomeGUI extends GenericProfessorGUI {

    @FXML
    public void showCourseBooks() {
        changePage(PagesGUI.COURSE_BOOKS_LIST_PROFESSOR);
    }

    @FXML
    public void insertCourseBook() {
        changePage(PagesGUI.INSERT_COURSE_BOOK_PROFESSOR);
    }

    @FXML
    public void removeCourseBook() {
        changePage(PagesGUI.REMOVE_COURSE_BOOK_PROFESSOR);
    }

}
