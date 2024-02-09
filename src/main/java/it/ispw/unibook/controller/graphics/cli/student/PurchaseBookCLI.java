package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.PurchaseBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;

public class PurchaseBookCLI extends ManageSellableBookCLI {

    PurchaseBookController controller = new PurchaseBookController();

    public void purchaseBook(SellableBookBean bean) throws SellableBookException, NegotiationException, SessionException {
        controller.purchaseBook(bean);
    }

}
