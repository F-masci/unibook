package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.application.InsertCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertBookGUI extends ManageBookGUI implements Initializable {

    private final InsertCourseBookController controller = new InsertCourseBookController();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField titleField;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button searchBookButton;
    @FXML Button saveBookButton;

    @FXML
    private Button insertManualButton;
    @FXML
    private Button retryButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button errorButton;

    private int courseSelected = 0;

    // FIXME exceptions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadCoursesComboBox(coursesCombo);
        } catch (SessionException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onCourseSelected(ActionEvent event) {
        int selected = getCourseSelectedFromComboBox(coursesCombo);
        if(selected == courseSelected) return;
        courseSelected = selected;

        isbnField.setDisable(false);

    }

    @FXML
    public void getBookInformation() {
        titleField.setText("");
        errorLabel.setText("");

        try {
            BookBean bean = new BookBean(isbnField.getText());
            searchBookButton.setVisible(false);
            isbnField.setDisable(true);
            controller.getBookInformation(bean);

            // Libro trovato
            titleField.setText(bean.getName());
            confirmButton.setVisible(true);
            errorButton.setVisible(true);
        } catch (BookException e) {
            errorLabel.setText(e.getMessage());
            Throwable cause = e.getCause();
            if(cause.getClass() == ISBNNotValidException.class) {               // isbn non valido
                searchBookButton.setVisible(true);
            } else if(cause.getClass() == BookNotFoundException.class) {        // Libro non trovato
                retryButton.setVisible(true);
                insertManualButton.setVisible(true);
            }
        }
    }

    @FXML
    public void retryInsertAuto() {
        errorLabel.setText("");
        isbnField.setText("");
        titleField.setText("");
        retryButton.setVisible(false);
        insertManualButton.setVisible(false);
        searchBookButton.setVisible(true);
        coursesCombo.setDisable(false);
        isbnField.setDisable(false);
    }

    @FXML
    public void manualInsert() {
        errorLabel.setText("");
        retryButton.setVisible(false);
        insertManualButton.setVisible(false);
        titleField.setDisable(false);
        isbnField.setDisable(false);
        saveBookButton.setVisible(true);
    }

    @FXML
    public void errorBook() {
        confirmButton.setVisible(false);
        errorButton.setVisible(false);
        retryButton.setVisible(true);
        insertManualButton.setVisible(true);
    }

    @FXML
    public void confirmBook() {
        errorLabel.setText("");
        confirmButton.setVisible(false);
        errorButton.setVisible(false);
        saveBookButton.setVisible(false);
        coursesCombo.setDisable(true);
        isbnField.setDisable(true);
        titleField.setDisable(true);

        try {
            ManageBookBean bean = new ManageBookBean(courseSelected, isbnField.getText(), titleField.getText());
            controller.insertBookInCourse(bean);
            successLabel.setText("Libro inserito correttamente");
        } catch(BookException e) {
            errorLabel.setText(e.getMessage());
            coursesCombo.setDisable(false);
            isbnField.setDisable(false);
            titleField.setDisable(false);
            saveBookButton.setVisible(true);
        } catch (CourseException e) {
            errorLabel.setText(e.getMessage());
            retryButton.setVisible(true);
            insertManualButton.setVisible(true);
        }


    }

}
