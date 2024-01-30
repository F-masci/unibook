package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Questa View raccoglie le funzioni comuni alle View per stampare le liste dei libri in vendita.
 */
public abstract class GenericPageManageSellableBookCLI extends GenericPageManageBookCLI {

    /**
     * Stampa a schermo la lista dei corsi presenti nel sistema
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageSellableBookCLI</i>
     */
    protected void printCoursesList(@NotNull ManageSellableBookCLI controller) {
        // Viene istanziato il bean per contenere i corsi
        CoursesListBean bean = new CoursesListBean();
        // Vengono richiesti i corsi al controller grafico
        controller.retrieveCourses(bean);
        // Viene stampata la lista associata al bean
        printCoursesListBean(bean, "CORSI DISPONIBILI");
    }

    /**
     * Stampa a schermo la lista dei libri in vendita nel sistema
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageSellableBookCLI</i>
     * @throws SessionException Viene lanciata nel caso in cui la sessione corrente non sia stata trovata
     */
    protected void printSellableBooksList(@NotNull ManageSellableBookCLI controller) throws SessionException {
        // Viene istanziato il bean per contenere i libri in vendita
        SellableBooksListBean bean = new SellableBooksListBean();
        // Vengono richiesti i libri in vendita al controller grafico
        controller.retrieveSellableBooksBySession(bean);
        // Viene stampata la lista associata al bean
        printSellableBooksListBean(bean, "LIBRI IN VENDITA");

    }

    /**
     * Stampa a schermo la lista degli acquirenti di un libro in vendita
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageSellableBookCLI</i>
     * @param sellableBookCode Codice del libro in vendita
     * @throws SellableBookException Viene lanciata nel caso in cui il libro in vendita non sia stato trovato
     */
    protected void printSellableBookActiveNegotiationsList(@NotNull ManageSellableBookCLI controller, int sellableBookCode) throws SellableBookException {
        try {
            // Viene istanziato il bean per contenere il libro in vendita
            SellableBookBean sellableBookBean = new SellableBookBean(sellableBookCode);
            // Vengono richiesti gli accounts al controller grafico
            AccountsListBean accountsListBean = controller.retrieveActiveNegotiationBySellableBook(sellableBookBean);
            // Viene stampata la lista associata al bean
            printAccountsListBean(accountsListBean, "TRATTATIVE IN CORSO");
        } catch(BookException ignored) {
            // Quest'eccezione può essere ignorata poiché viene sollevata nel caso in cui si inserisce un ISBN non valido
        }
    }

    /**
     * Stampa a schermo la lista dei libri che l'utente loggato è interessato ad acquistare
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageSellableBookCLI</i>
     * @throws SessionException Viene lanciata nel caso in cui la sessione non sia stata trovata
     */
    protected void printActiveNegotiationsList(@NotNull ManageSellableBookCLI controller) throws SessionException {
        // Viene istanziato il bean per contenere il libro in vendita
        SellableBooksListBean bean = new SellableBooksListBean();
        // Vengono richiesti i libri in vendita al controller grafico
        controller.retrieveSellableBooksBySessionActiveNegotiation(bean);
        // Viene stampata la lista associata al bean
        printSellableBooksListBean(bean, "LIBRI CHE STAI ACQUISTANDO");
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
     * Metodo ausiliare per stampare la lista dei libri in vendita in un bean
     * @param bean Deve contenere la lista dei libri
     * @param msg Messaggio da stampare prima della lista
     */
    protected void printSellableBooksListBean(@NotNull SellableBooksListBean bean, String msg) {
        // Viene estratta la lista dei libri
        List<SellableBookBean> sellableBooks = bean.getList();
        if(msg != null)Printer.println("\n---" + msg + "---");
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
        if(msg != null)Printer.println("\n---" + msg + "---");
        for (AccountBean a : accounts) {
            Printer.println("[" + a.getCode() + "] " + a);
        }
        Printer.println("");
    }

}
