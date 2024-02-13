package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.application.LoginController;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.utils.SessionManager;

public class LoginCLI {

    // Controller applicativo per eseguire il login
    private final LoginController controller = new LoginController();

    public void login(LoginBean bean) throws LoginException {
        try {
            // Viene eseguito il login
            controller.login(bean);

            // Se non vengono sollevate eccezioni il login è stato eseguito correttamente
            Printer.println("");
            // Nel bean viene l'ID della sessione corrispondente
            // A seconda del ruolo dell'utente loggato viene istanziata la nuova View
            switch (SessionManager.getAccountTypeBySessionID(bean.getSessionId())) {
                case STUDENT -> new it.ispw.unibook.view.cli.student.PageHomeCLI().display();
                case PROFESSOR -> new it.ispw.unibook.view.cli.professor.PageHomeCLI().display();
                default -> throw new SessionException("Errore durante la ricerca della sessione corrente");
            }
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
