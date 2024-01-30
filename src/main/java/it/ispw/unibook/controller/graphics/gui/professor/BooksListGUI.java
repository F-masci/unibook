package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.controller.application.ManageCourseBookController;
import it.ispw.unibook.exceptions.course.CourseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BooksListGUI extends ManageBookGUI implements Initializable {

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private VBox booksList;


    private final ManageCourseBookController controller = new ManageCourseBookController();
    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCoursesComboBox(coursesCombo);
    }

    // FIXME exceptions
    @FXML
    public void loadBooksList(ActionEvent event) throws CourseException {
        int selected = getCourseSelectedFromComboBox(coursesCombo);
        if(selected == courseSelected) return;
        courseSelected = selected;

        BooksListBean bean = new BooksListBean(courseSelected);
        super.retrieveBooksByCourse(bean);
        List<BookBean> books = bean.getList();

        booksList.getChildren().clear();
        for (BookBean b : books) {
            String text = b.getISBN() + " - " + b.toString();
            Button book = new Button(text);
            book.getStyleClass().add("book");
            booksList.getChildren().add(book);
        }
    }
}
