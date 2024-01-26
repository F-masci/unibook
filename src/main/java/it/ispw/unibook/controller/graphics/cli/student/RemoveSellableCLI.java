package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.controller.application.InsertSellableBookController;
import it.ispw.unibook.controller.application.RemoveSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;

public class RemoveSellableCLI extends GenericStudentCLI {

    RemoveSellableBookController controller = new RemoveSellableBookController();

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) {
        controller.retrieveSellableBooksBySession(bean);
    }

    public void removeSellableBook(SellableBookBean bean) throws SellableBookException {
        controller.removeSellableBook(bean);
    }

}
