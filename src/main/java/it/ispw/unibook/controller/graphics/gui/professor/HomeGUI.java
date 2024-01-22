package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.InsertCourseBookController;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeGUI extends GenericGUI {

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
