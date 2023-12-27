package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.application.LoginController;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.EmailNotValidException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.SessionManager;

public class LoginCLI {

    private final LoginController controller = new LoginController();

    public void login(String email, String password) throws EmailNotValidException, EmailNotFoundException, IncorrectPasswordException {
        LoginBean bean = new LoginBean(email, password);
        controller.login(bean);

        // Se non vengono sollevate eccezioni dal controller applicativo l'utente viene mandato alla home
        updateView(bean);
    }

    private void updateView(LoginBean bean) {
        switch(SessionManager.getAccountTypeBySessionID(bean.getSessionId())) {
            case STUDENT -> {
                it.ispw.unibook.view.cli.student.PageHomeCLI home = new it.ispw.unibook.view.cli.student.PageHomeCLI();
                home.init();
            }
            case PROFESSOR -> {
                it.ispw.unibook.view.cli.professor.PageHomeCLI home = new it.ispw.unibook.view.cli.professor.PageHomeCLI();
                home.display();
            }
            case null -> { break; }
        }
    }

}
