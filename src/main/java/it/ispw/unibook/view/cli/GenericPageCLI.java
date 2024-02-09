package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.utils.Printer;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Questa View raccoglie le funzioni e gli attributi comuni alle View CLI.
 */
public abstract class GenericPageCLI {

    private static final String TITLE_SEPARATOR = "---";

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

    /**
     * Richiede all'utente di inserire un codice di un libro (ISBN)
     * @return ISBN inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected String requestBookCode() throws EscCliException {
        return requestBookCode("ISBN del libro (oppure esc per uscire): ");
    }

    /**
     * Richiede all'utente di inserire un codice di un libro (ISBN)
     * @param msg Messaggio da stampare per la richiesta del codice
     * @return ISBN inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected String requestBookCode(String msg) throws EscCliException {
        // Richiama la funzione più generale per richiedere una stringa
        return requestString(msg);
    }

    /**
     * Richiede all'utente di inserire un codice di un corso
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestCourseCode() throws EscCliException {
        return requestCourseCode("Codice del corso (oppure esc per uscire): ");
    }
    /**
     * Richiede all'utente di inserire un codice di un corso
     * @param msg Messaggio da stampare per la richiesta del codice
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestCourseCode(String msg) throws EscCliException {
        // Richiama la funzione più generale per richiedere un intero
        return requestInt(msg);
    }

    /**
     * Richiede all'utente di inserire un codice di un libro in vendita
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestSellableBookCode() throws EscCliException {
        return requestSellableBookCode("Codice del libro in vendita (oppure esc per uscire): ");
    }
    /**
     * Richiede all'utente di inserire un codice di un libro in vendita
     * @param msg Messaggio da stampare per la richiesta del codice
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestSellableBookCode(String msg) throws EscCliException {
        // Richiama la funzione più generale per richiedere un intero
        return requestInt(msg);
    }

    /**
     * Richiede all'utente di inserire un codice di uno studente
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestAccountCode() throws EscCliException {
        return requestAccountCode("Codice dello studente (oppure esc per uscire): ");
    }
    /**
     * Richiede all'utente di inserire un codice di uno studente
     * @param msg Messaggio da stampare per la richiesta del codice
     * @return Codice inserito
     * @throws EscCliException L'utente ha inserite <i>esc</i> per tornare indietro
     */
    protected int requestAccountCode(String msg) throws EscCliException {
        // Richiama la funzione più generale per richiedere un intero
        return requestInt(msg);
    }

    /**
     * Metodo ausiliare per stampare la lista dei corsi in un bean
     * @param bean Deve contenere la lista dei corsi
     * @param msg Messaggio da stampare prima della lista
     */
    protected void printCoursesListBean(@NotNull CoursesListBean bean, String msg) {
        // Viene estratta la lista dei corsi
        List<CourseBean> courses = bean.getList();
        if(msg != null) Printer.println("\n" + TITLE_SEPARATOR + msg + TITLE_SEPARATOR);
        // Vengono stampati i corsi utilizzando il metodo <i>toString</i> del bean
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c);
        }
        Printer.println("");
    }

    /**
     * Metodo ausiliare per stampare la lista dei libri in un bean
     * @param bean Deve contenere la lista dei libri
     * @param msg Messaggio da stampare prima della lista
     */
    protected void printCourseBooksListBean(@NotNull BooksListBean bean, String msg) {
        // Viene estratta la lista dei libri
        List<BookBean> books = bean.getList();
        if(msg != null) Printer.println("\n" + TITLE_SEPARATOR + msg + TITLE_SEPARATOR);
        // Vengono stampati i libri utilizzando il metodo <i>toString</i> del bean
        for (BookBean b : books) {
            Printer.println("[" + b.getISBN() + "] " + b);
        }
        Printer.println("");
    }

    /**
     * Metodo ausiliare per stampare la lista dei libri in vendita in un bean
     * @param bean Deve contenere la lista dei libri
     * @param msg Messaggio da stampare prima della lista
     */
    protected void printSellableBooksListBean(@NotNull SellableBooksListBean bean, String msg) {
        // Viene estratta la lista dei libri
        List<SellableBookBean> sellableBooks = bean.getList();
        if(msg != null) Printer.println("\n" + TITLE_SEPARATOR + msg + TITLE_SEPARATOR);
        // Vengono stampati i libri utilizzando il metodo <i>toString</i> del bean
        for (SellableBookBean b : sellableBooks) {
            Printer.println("[" + b.getCode() + "] " + b);
        }
        Printer.println("");
    }

    /**
     * Metodo ausiliare per stampare la lista degli account in un bean
     * @param bean Deve contenere la lista degli account
     * @param msg Messaggio da stampare prima della lista
     */
    protected void printAccountsListBean(@NotNull AccountsListBean bean, String msg) {
        // Viene estratta la lista degli account
        List<AccountBean> accounts = bean.getList();
        // Vengono stampati gli account utilizzando il metodo <i>toString</i> del bean
        if(msg != null) Printer.println("\n" + TITLE_SEPARATOR + msg + TITLE_SEPARATOR);
        for (AccountBean a : accounts) {
            Printer.println("[" + a.getCode() + "] " + a);
        }
        Printer.println("");
    }

}
