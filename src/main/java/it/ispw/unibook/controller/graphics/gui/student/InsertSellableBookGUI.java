package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.InsertSellableBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
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

public class InsertSellableBookGUI extends ManageSellableBookGUI implements Initializable {

    private final InsertSellableBookController controller = new InsertSellableBookController();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> booksCombo;

    @FXML
    private TextField priceField;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button insertSellableBookButton;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCourses(coursesCombo);
    }

    @FXML
    public void onCourseSelected(ActionEvent event) {
        try {
            int selected = super.getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            courseSelected = selected;
            loadCourseBooks(booksCombo, selected);
            booksCombo.setDisable(false);
        } catch(ComboSelectionNotValidException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void insertSellableBook() {
        errorLabel.setText("");

        try {
            String isbn = super.getCourseBookSelectedFromComboBox(booksCombo);
            Float price = Float.valueOf(priceField.getText());
            SellableBookBean bean = new SellableBookBean(courseSelected, isbn, price);
            controller.insertSellableBook(bean);
            coursesCombo.setDisable(true);
            booksCombo.setDisable(true);
            priceField.setDisable(true);
            insertSellableBookButton.setVisible(false);
            successLabel.setText("Libro inserito correttamente in vendita");
        } catch (BookException | ComboSelectionNotValidException | SellableBookException | SessionException | CourseException | NumberFormatException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
