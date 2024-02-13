package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.RemoveSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveSellableBookGUI extends ManageSellableBookGUI implements Initializable {

    private final RemoveSellableBookController controller = new RemoveSellableBookController();

    @FXML
    private ComboBox<String> sellableBooksCombo;

    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;

    @FXML
    private Button removeSellableBookButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSessionSellableBooks(sellableBooksCombo);
    }

    @FXML
    public void removeSellableBook() {
        errorLabel.setText("");

        try {
            int code = super.getSellableBookSelectedFromComboBox(sellableBooksCombo);
            SellableBookBean bean = new SellableBookBean(code);
            controller.removeSellableBook(bean);
            removeSellableBookButton.setVisible(false);
            sellableBooksCombo.setDisable(true);
            successLabel.setText("Libro in vendita rimosso correttamente");
        } catch (ComboSelectionNotValidException | SellableBookException | SessionException | CourseException e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
