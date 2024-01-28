package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.RemoveSellableBookController;
import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.login.SessionException;

public class RemoveSellableBookCLI extends ManageSellableBookCLI {

    RemoveSellableBookController controller = new RemoveSellableBookController();

    public void removeSellableBook(SellableBookBean bean) throws SellableBookException, SessionException {
        controller.removeSellableBook(bean);
    }

}
