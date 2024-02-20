package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.graphics.cli.student.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.view.cli.GenericPageCLI;
import org.jetbrains.annotations.NotNull;

/**
 * Questa View raccoglie le funzioni comuni alle View per stampare le liste dei libri in vendita.
 */
public abstract class GenericPageManageSellableBookCLI extends GenericPageCLI {

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
     * Stampa a schermo la lista dei libri associati a un corso
     * @param controller Controller grafico della view che effettua la richiesta.
     *                   Tale controller deve essere una classe figlia di <i>ManageBookCLI</i>
     * @param courseCode Codice del corso di cui si vuole stampare la lista dei libri
     * @throws CourseException Viene lanciata nel caso in cui il corso non sia stato trovato
     */
    protected void printCourseBooksList(ManageSellableBookCLI controller, int courseCode) throws CourseException {
        // Viene istanziato il bean per contenere i corsi
        BooksListBean bean = new BooksListBean(courseCode);
        // Vengono richiesti i libri al controller grafico
        controller.retrieveBooksByCourse(bean);
        // Viene stampata la lista associata al bean
        printCourseBooksListBean(bean, "LIBRI ASSOCAITI AL CORSO");
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
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    protected void printSellableBookActiveNegotiationsList(@NotNull ManageSellableBookCLI controller, int sellableBookCode) throws SellableBookException, SessionException {
        // Viene istanziato il bean per contenere il libro in vendita
        SellableBookBean sellableBookBean = new SellableBookBean(sellableBookCode);
        // Vengono richiesti gli accounts al controller grafico
        AccountsListBean accountsListBean = controller.retrieveActiveNegotiationBySellableBook(sellableBookBean);
        // Viene stampata la lista associata al bean
        printAccountsListBean(accountsListBean, "TRATTATIVE IN CORSO");
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
        controller.retrieveSellableBooksByActiveNegotiationOfSession(bean);
        // Viene stampata la lista associata al bean
        printSellableBooksListBean(bean, "LIBRI CHE STAI ACQUISTANDO", b -> "[" + b.getCode() + "] " + b + " - " + b.getEmailSeller());
    }

}
