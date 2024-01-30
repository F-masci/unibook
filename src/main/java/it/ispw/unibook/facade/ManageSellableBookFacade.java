package it.ispw.unibook.facade;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.SellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import org.jetbrains.annotations.NotNull;

public class ManageSellableBookFacade {

    SellableBookController sellableBookController = new SellableBookController();

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

    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookNotFoundException {
        return sellableBookController.retrieveActiveNegotiationBySellableBook(bean);
    }

}