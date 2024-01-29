package it.ispw.unibook.view.cli;

import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenericPageCLI {

    protected GenericPageCLI() {}

    protected final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    protected void showErrorMessage(Exception e) {
        Printer.error(e);
    }

    protected void showErrorMessage(String msg) {
        Printer.error(msg);
    }

    protected void waitForExit() {
        Printer.println("Premi un qualsisi tasto per tornare alla home");
        try {
            String ignored = br.readLine();
        } catch (IOException e) {
            Printer.error("Errore durante la lettura dell'input");
            System.exit(-1);
        }
    }

    protected String requestString() throws EscCliException {
        return requestString(null);
    }
    protected String requestString(String msg) throws EscCliException {
        String res = null;
        try {
            if(msg != null) Printer.print(msg);
            res = br.readLine();
            if (res.equals("esc")) throw new EscCliException();
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return res;
    }

    protected int requestInt() throws EscCliException {
        return requestInt(null);
    }
    protected int requestInt(String msg) throws EscCliException {
        while (true) {
            try {
                return Integer.parseInt(requestString(msg));
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            }
        }
    }

}
