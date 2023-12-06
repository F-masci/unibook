package it.ispw.unibook.controller.grafici.gui;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.boundary.LoginBoundary;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginGUI {

    final ControllerGUI gui = ControllerGUI.getInstance();

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    public void login() {
        LoginBean bean = new LoginBean(emailField.getText(), passwordField.getText());
        LoginBoundary boundary = new LoginBoundary(bean);
        boundary.login();
    }
}
