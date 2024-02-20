package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import org.jetbrains.annotations.NotNull;

public abstract class ManageSellableBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade manageSellableBookFacade = new ManageSellableBookFacade();

    /**
     * Ritorna la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi
     */
    public void retrieveCourses(@NotNull CoursesListBean bean) {
        manageSellableBookFacade.retrieveCourses(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public void retrieveBooksByCourse(@NotNull BooksListBean bean) throws CourseException {
        manageSellableBookFacade.retrieveBooksByCourse(bean);
    }

    /**
     * Carica nel bean la lista dei libri in vendita dell'utente loggato
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita collegati
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksBySession(@NotNull SellableBooksListBean bean) throws SessionException {
        manageSellableBookFacade.retrieveSellableBooksBySession(bean);
    }

    /**
     * Carica nel bean la lista dei libri che l'utente loggato è interessato ad acquistare
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksByActiveNegotiationOfSession(@NotNull SellableBooksListBean bean) throws SessionException {
        manageSellableBookFacade.retrieveSellableBooksByActiveNegotiationOfSession(bean);
    }

    /**
     * Ritorna la lista dei libri in vendita cercandoli tramite ISBN
     * @param bean Deve contenere l'ISBN
     * @return Bean contenente la lista dei libri in vendita con l'ISBN fornito
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public SellableBooksListBean retrieveSellableBooksByIsbn(@NotNull BookBean bean) throws SessionException {
        return manageSellableBookFacade.retrieveSellableBooksByIsbn(bean);
    }

    /**
     * Ritorna la lista dei libri in vendita collegati al corso
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente la lista dei libri in vendita collegati al corso fornito
     * @throws CourseException Viene sollevata se il corso non viene trovato
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public SellableBooksListBean retrieveSellableBooksByCourse(@NotNull CourseBean bean) throws CourseException, SessionException {
        return manageSellableBookFacade.retrieveSellableBooksByCourse(bean);
    }

    /**
     * Ritorna la lista degli acquirenti collegati al libro in vendita
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente i libri in vendita collegati al corso
     * @throws SellableBookException Viene sollevata se il libro in vendita non viene trovato
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public AccountsListBean retrieveActiveNegotiationBySellableBook(@NotNull SellableBookBean bean) throws SellableBookException, SessionException {
        return manageSellableBookFacade.retrieveActiveNegotiationBySellableBook(bean);
    }

}
