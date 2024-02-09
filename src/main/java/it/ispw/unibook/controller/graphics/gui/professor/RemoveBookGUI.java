package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveBookGUI extends ManageBookGUI implements Initializable {

    private final RemoveCourseBookController controller = new RemoveCourseBookController();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> booksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML Button removeBookButton;

    private int courseSelected = 0;

    // FIXME exceptions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSessionCourses(coursesCombo);
    }

    // FIXME exceptions
    @FXML
    public void loadBooksList(ActionEvent event) throws CourseException {
        try {
            int selected = getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            courseSelected = selected;
            loadCourseBooks(booksCombo, selected);
        } catch (ComboSelectionNotValidException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void removeBook() {
        errorLabel.setText("");
        String isbn = null;

        try {
            isbn = getCourseBookSelectedFromComboBox(booksCombo);
        } catch (ComboSelectionNotValidException e) {
            errorLabel.setText(e.getMessage());
            return;
        }

        try {
            CourseBean courseBean = new CourseBean(courseSelected);
            BookBean bookBean = new BookBean(isbn);
            controller.removeBookFromCourse(courseBean, bookBean);

            coursesCombo.setDisable(true);
            booksCombo.setDisable(true);
            removeBookButton.setVisible(false);
            successLabel.setText("Libro rimosso correttamente");
        } catch (BookException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
