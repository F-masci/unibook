package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
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

    private final ObservableMap<String, String> items = FXCollections.observableHashMap();

    // FIXME exceptions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCoursesComboBox(coursesCombo);
        } catch (SessionException e) {
            throw new RuntimeException(e);
        }
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

        items.clear();

        for(BookBean b: books) {
            items.put(b.getISBN() + " - " + b.toString(), b.getISBN());
        }

        booksCombo.setItems(FXCollections.observableArrayList(items.keySet()));
        booksCombo.setDisable(false);

    }

    @FXML
    public void removeBook() {

        errorLabel.setText("");

        String value = booksCombo.getValue();
        String isbn = "";
        if (value != null) isbn = items.get(value);

        try {
            ManageBookBean bean = new ManageBookBean(courseSelected, isbn);
            controller.removeBookFromCourse(bean);

            coursesCombo.setDisable(true);
            booksCombo.setDisable(true);
            removeBookButton.setVisible(false);
            successLabel.setText("Libro rimosso correttamente");
        } catch (BookException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
