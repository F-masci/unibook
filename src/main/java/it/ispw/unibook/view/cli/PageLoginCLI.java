package it.ispw.unibook.view.cli;

import it.ispw.unibook.controller.graphics.cli.LoginCLI;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.EmailNotValidException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.Printer;

public class PageLoginCLI extends GenericProfessorPageCLI {

    private final LoginCLI controller = new LoginCLI();

    public void init() {
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
                email = "professore@uniroma2.eu";
                Printer.println(email);

                Printer.print("Password: ");
                // password = br.readLine();
                password = "professore";
                Printer.println(password);

                controller.login(email, password);
                break;

            } /* catch (IOException e) {
                    Printer.error(e);
                    System.exit(-1);
            } */catch (EmailNotValidException | EmailNotFoundException | IncorrectPasswordException e) {
                showErrorMessage(e);
            }
        }

    }

}
