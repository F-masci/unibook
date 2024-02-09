package it.ispw.unibook.facade;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.controller.application.InsertSellableBookController;
import it.ispw.unibook.controller.application.SellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import org.jetbrains.annotations.NotNull;

public class ManageSellableBookFacade {

    // Controller per ottenere le informazioni dei corsi
    private final CourseController courseController = new CourseController();
    // Controller per ottenere l'informazione dei libri
    private final BookController bookController = new BookController();
    // Controller per ottenere l'informazione dei libri in vendita
    private final SellableBookController sellableBookController = new SellableBookController();

    private final InsertSellableBookController insertSellableBookController = new InsertSellableBookController();

    /**
     * Ritorna la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi
     */
    public void retrieveCourses(@NotNull CoursesListBean bean) {
        courseController.retrieveCourses(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public void retrieveBooksByCourse(@NotNull BooksListBean bean) throws CourseException {
        bookController.retrieveBooksByCourse(bean);
    }

    public void retrieveSellableBooksBySession(@NotNull SellableBooksListBean bean) throws SessionException {
        sellableBookController.retrieveSellableBooksBySession(bean);
    }
    public void retrieveSellableBooksBySessionActiveNegotiation(@NotNull SellableBooksListBean bean) throws SessionException {
        sellableBookController.retrieveSellableBooksBySessionActiveNegotiation(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        return sellableBookController.retrieveSellableBooksByIsbn(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) throws CourseNotFoundException {
        return sellableBookController.retrieveSellableBooksByCourse(bean);
    }

    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookException {
        return sellableBookController.retrieveActiveNegotiationBySellableBook(bean);
    }

    /*-- INSERIMENTO --*/

    public void insertSellableBook(SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        insertSellableBookController.insertSellableBook(bean);
    }

}
