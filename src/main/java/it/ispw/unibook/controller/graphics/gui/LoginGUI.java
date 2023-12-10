package it.ispw.unibook.controller.graphics.gui;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.application.LoginController;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.EmailNotValidException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginGUI {

    final ControllerGUI gui = ControllerGUI.getInstance();

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void login() {
        try {
            LoginBean bean = new LoginBean(emailField.getText(), passwordField.getText());
            LoginController controller = new LoginController(bean);
            controller.login();
            gui.setPage(PagesGUI.HOME_PROFESSOR);
        } catch (EmailNotValidException | EmailNotFoundException | IncorrectPasswordException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
