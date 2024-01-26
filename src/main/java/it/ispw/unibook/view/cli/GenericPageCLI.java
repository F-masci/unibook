package it.ispw.unibook.view.cli;

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

}
