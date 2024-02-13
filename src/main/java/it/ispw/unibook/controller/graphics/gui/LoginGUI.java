package it.ispw.unibook.controller.graphics.gui;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.application.LoginController;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginGUI extends GenericGUI{

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void login() {
        try {
            // Viene istanziato il bean a partire dalle credenziali inserite
            LoginBean bean = new LoginBean(emailField.getText(), passwordField.getText());
            // Viene istanziato il controller per eseguire il login
            LoginController controller = new LoginController();
            // Viene seguito il login
            // Se non vengono sollevate eccezioni il login viene eseguito correttamente
            controller.login(bean);
            // Nel bean viene l'ID della sessione corrispondente
            // A seconda del ruolo dell'utente loggato viene istanziata la nuova View
            switch(SessionManager.getAccountTypeBySessionID(bean.getSessionId())) {
                case STUDENT -> changePage(PagesGUI.HOME_STUDENT);
                case PROFESSOR -> changePage(PagesGUI.HOME_PROFESSOR);
                default -> { break; }
            }
        } catch (FieldNotValidException | LoginException | SessionException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
