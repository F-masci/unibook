package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import org.jetbrains.annotations.NotNull;

public abstract class ManageSellableBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    /**
     * Ritorna la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi
     */
    public void retrieveCourses(CoursesListBean bean) {
        facade.retrieveCourses(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        facade.retrieveBooksByCourse(bean);
    }

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        facade.retrieveSellableBooksBySession(bean);
    }
    public void retrieveSellableBooksByActiveNegotiationOfSession(@NotNull SellableBooksListBean bean) throws SessionException {
        facade.retrieveSellableBooksByActiveNegotiationOfSession(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        return facade.retrieveSellableBooksByIsbn(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) throws CourseException {
        return facade.retrieveSellableBooksByCourse(bean);
    }

    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookException {
        return facade.retrieveActiveNegotiationBySellableBook(bean);
    }

}
