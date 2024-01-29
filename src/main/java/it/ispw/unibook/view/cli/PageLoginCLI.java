package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.graphics.cli.LoginCLI;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.utils.Printer;

public class PageLoginCLI extends GenericPageCLI implements PageCLI {

    private final LoginCLI controller = new LoginCLI();

    @Override
    public void display() {
        Printer.clear();

        Printer.println("Benevenuti su Unibook!");
        Printer.println("Eseguite l'accesso per entrare nel sistema");

        requestCredentials();

    }

    private void requestCredentials() {

        while(true) {

            try {

                String email = null;
                String password = null;

                Printer.print("Email: ");
                // email = br.readLine();
                //email = "studente@students.uniroma2.eu";
                email = "professore@uniroma2.eu";
                Printer.println(email);

                Printer.print("Password: ");
                // password = br.readLine();
                //password = "studente";
                password = "professore";
                Printer.println(password);

                LoginBean bean = new LoginBean(email, password);
                controller.login(bean);
                break;

            } /* catch (IOException e) {
                    Printer.error(e);
                    System.exit(-1);
            } */catch (LoginException | FieldNotValidException e) {
                showErrorMessage(e);
            }
        }

    }

}
