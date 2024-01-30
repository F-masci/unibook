package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.ManageSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import org.jetbrains.annotations.NotNull;

public class ManageSellableBookCLI extends ManageBookCLI {

    ManageSellableBookController sellbaleBookController = new ManageSellableBookController();

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        sellbaleBookController.retrieveSellableBooksBySession(bean);
    }
    public void retrieveSellableBooksBySessionActiveNegotiation(@NotNull SellableBooksListBean bean) throws SessionException {
        sellbaleBookController.retrieveSellableBooksBySessionActiveNegotiation(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        return sellbaleBookController.retrieveSellableBooksByIsbn(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) {
        return sellbaleBookController.retrieveSellableBooksByCourse(bean);
    }

    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookNotFoundException {
        return sellbaleBookController.retrieveActiveNegotiationBySellableBook(bean);
    }

}
