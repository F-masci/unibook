package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.PurchaseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.utils.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchByCourseGUI extends ManageSellableBookGUI implements Initializable {

    private final PurchaseBookController controller = new PurchaseBookController();

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML Button purchaseBookButton;

    private int courseSelected = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllCourses(coursesCombo);
    }

    @FXML
    public void onCourseSelected(ActionEvent event) {
        try {
            int selected = getCourseSelectedFromComboBox(coursesCombo);
            if (selected == courseSelected) return;
            courseSelected = selected;
            loadCourseSellableBooks(sellableBooksCombo, selected);
            sellableBooksCombo.setDisable(false);
            purchaseBookButton.setDisable(false);
        } catch (CourseException e) {
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setDisable(true);
            errorLabel.setText(e.getMessage());
        } catch(ComboSelectionNotValidException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @FXML
    public void purchaseBook() {
        try {
            int selected = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            SellableBookBean bean = new SellableBookBean(selected);
            controller.purchaseBook(bean);
            coursesCombo.setDisable(true);
            sellableBooksCombo.setDisable(true);
            purchaseBookButton.setVisible(false);
            successLabel.setText("Trattativa iniziata correttamente");
        } catch (BookException | ComboSelectionNotValidException | SellableBookException | NegotiationException | SessionException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
