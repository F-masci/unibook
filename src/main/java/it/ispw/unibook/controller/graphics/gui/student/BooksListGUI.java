package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BooksListGUI extends ManageSellableBookGUI implements Initializable {

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private VBox booksList;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCourses(coursesCombo);
    }

    // FIXME exceptions
    @FXML
    public void onCourseSelected(ActionEvent event) throws CourseException {
        try {
            int selected = getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            courseSelected = selected;

            BooksListBean bean = new BooksListBean(courseSelected);
            super.retrieveBooksByCourse(bean);
            List<BookBean> books = bean.getList();

            booksList.getChildren().clear();
            for (BookBean b : books) {
                String text = b.getISBN() + " - " + b.toString();
                Label label = new Label(text);
                label.getStyleClass().add("book");
                booksList.getChildren().add(label);
            }
        } catch (ComboSelectionNotValidException ignored) {}
    }
}
