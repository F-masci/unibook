package it.ispw.unibook.view.cli;

import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Questa View raccoglie le funzioni e gli attributi comuni alle View CLI.
 */
public abstract class GenericPageCLI {

    // Contiene il BufferedReader utilizzato dalle funzioni delle pagine CLI per richiedere dati all'utente
    protected final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Mostra il messaggio di errore associato all'<i>Exception</i>
     * @param e <i>Exception</i> da stmpare
     */
    protected void showErrorMessage(Exception e) {
        Printer.error(e);
    }

    /**
     * Mostra un messaggio di errore
     * @param msg Messaggio da stmpare
     */
    protected void showErrorMessage(String msg) {
        Printer.error(msg);
    }

    /**
     * Interrompe il flusso applicativo fino a quando l'utente non preme il tasto invio
     */
    protected void waitForExit() {
        try {
            requestString("Premi il tasto invio per tornare alla home\n");
        } catch (EscCliException e) {
            // Anche se viene sollevato l'input della funzione verrà scartato
            // per cui l'eccezione può essere ignorata
        }
    }

    /**
     * Richiede una stringa all'utente
     * @return La stringa immessa
     * @throws EscCliException Viene sollevata se l'utente inserisce esc per tornare indietro
     */
    protected String requestString() throws EscCliException {
        return requestString(null);
    }
    /**
     * Richiede una stringa all'utente
     * @param msg Messaggio da stampare prima di richiedere la stringa.
     *            Se <i>null</i> non viene stampato alcun messaggio.
     * @return La stringa immessa
     * @throws EscCliException Viene sollevata se l'utente inserisce esc per tornare indietro
     */
    protected String requestString(String msg) throws EscCliException {
        String res = null;
        try {
            // Se presente un messaggio lo stampo
            if(msg != null) Printer.print(msg);
            // Leggo una riga dall'input
            res = br.readLine();
            // Se la linea inserita è esc sollevo l'eccezione
            if (res.equals("esc")) throw new EscCliException();
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Ritorno la stringa
        return res;
    }

    /**
     * Richiede un intero all'utente
     * @return L'intero immesso
     * @throws EscCliException Viene sollevata se l'utente inserisce esc per tornare indietro
     */
    protected int requestInt() throws EscCliException {
        return requestInt(null);
    }
    /**
     * Richiede un intero all'utente
     * @param msg Messaggio da stampare prima di richiedere l'intero
     * @return L'intero immesso
     * @throws EscCliException Viene sollevata se l'utente inserisce esc per tornare indietro
     */
    protected int requestInt(String msg) throws EscCliException {
        while (true) {
            try {
                // Viene richiesta una stringa all'utente e poi viene eseguito il parsing
                return Integer.parseInt(requestString(msg));
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            }
        }
    }

    /**
     * Richiede un decimale all'utente
     * @return Il decimale immesso
     * @throws EscCliException Viene sollevata se l'utente inserisce esc per tornare indietro
     */
    protected float requestFloat() throws EscCliException {
        return requestFloat(null);
    }
    /**
     * Richiede un decimale all'utente
     * @param msg Messaggio da stampare prima di richiedere il decimale
     * @return Il decimale immesso
     * @throws EscCliException Viene sollevata se l'utente inserisce esc per tornare indietro
     */
    protected float requestFloat(String msg) throws EscCliException {
        while (true) {
            try {
                // Viene richiesta una stringa all'utente e poi viene eseguito il parsing
                return Float.parseFloat(requestString(msg));
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            }
        }
    }

}
