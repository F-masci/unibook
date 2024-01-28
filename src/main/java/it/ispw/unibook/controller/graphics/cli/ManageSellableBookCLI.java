package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.controller.application.ManageSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;

public class ManageSellableBookCLI extends ManageBookCLI {

    ManageSellableBookController controller = new ManageSellableBookController();

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        controller.retrieveSellableBooksBySession(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        return controller.retrieveSellableBooksByIsbn(bean);
    }

    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) {
        return controller.retrieveSellableBooksByCourse(bean);
    }

    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookNotFoundException {
        return controller.retrieveActiveNegotiationBySellableBook(bean);
    }

}
