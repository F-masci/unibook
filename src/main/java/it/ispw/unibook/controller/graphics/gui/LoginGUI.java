package it.ispw.unibook.controller.graphics.gui;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.application.LoginController;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.EmailNotValidException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginGUI {

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
            LoginController controller = new LoginController();
            controller.login(bean);
            switch(SessionManager.getAccountTypeBySessionID(bean.getSessionId())) {
                case STUDENT -> ControllerGUI.setPage(PagesGUI.HOME_STUDENT);
                case PROFESSOR -> ControllerGUI.setPage(PagesGUI.HOME_PROFESSOR);
                case null -> { break; }
            }
        } catch (EmailNotValidException | EmailNotFoundException | IncorrectPasswordException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
