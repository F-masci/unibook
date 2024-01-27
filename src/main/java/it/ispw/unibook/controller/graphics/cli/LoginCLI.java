package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.application.LoginController;
import it.ispw.unibook.exceptions.EmailNotValidException;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.utils.SessionManager;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.student.PageHomeCLI;

public class LoginCLI {

    private final LoginController controller = new LoginController();

    public void login(LoginBean bean) throws LoginException {
        controller.login(bean);

        // Se non vengono sollevate eccezioni dal controller applicativo l'utente viene mandato alla home
        updateView(bean);
    }

    private void updateView(LoginBean bean) {
        try {
            switch (SessionManager.getAccountTypeBySessionID(bean.getSessionId())) {
                case STUDENT -> {
                    new it.ispw.unibook.view.cli.student.PageHomeCLI().display();

                }
                case PROFESSOR -> {
                    new it.ispw.unibook.view.cli.professor.PageHomeCLI().display();
                }
                default -> {
                    throw new SessionException("Errore durante la ricerca della sessione corrente");
                }
            }
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
