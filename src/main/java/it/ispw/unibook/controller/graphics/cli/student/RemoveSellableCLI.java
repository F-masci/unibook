package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.controller.application.RemoveSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.login.SessionException;

public class RemoveSellableCLI extends GenericStudentCLI {

    RemoveSellableBookController controller = new RemoveSellableBookController();

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        controller.retrieveSellableBooksBySession(bean);
    }

    public void removeSellableBook(SellableBookBean bean) throws SellableBookException, SessionException {
        controller.removeSellableBook(bean);
    }

}
