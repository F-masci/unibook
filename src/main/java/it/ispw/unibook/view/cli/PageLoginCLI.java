package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.graphics.cli.LoginCLI;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.utils.Printer;

public class PageLoginCLI extends GenericPageCLI implements PageCLI {

    // Controller grafico relativo alla View
    private final LoginCLI controller = new LoginCLI();

    @Override
    public void display() {
        while(true) {
            Printer.clear();

            Printer.println("Benevenuti su Unibook!");
            Printer.println("Eseguite l'accesso per entrare nel sistema oppure digitate esc per uscire");

            // Richiede le credenziali di accesso al sistema
            if(requestCredentials()) continue;

            break;
        }

    }

    private boolean requestCredentials() {

        while(true) {

            try {

                // Richiede le credenziali all'utente
                String email = requestString("Email: ");
                String password = requestString("Password: ");

                // Effettua il login
                LoginBean bean = new LoginBean(email, password);
                controller.login(bean);
                break;

            } catch (LoginException | FieldNotValidException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return false;
            }
        }

        return true;

    }

}
