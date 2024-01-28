package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.MarkSellableBookSoldController;
import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;

public class MarkSellableBookSoldCLI extends ManageSellableBookCLI {

    MarkSellableBookSoldController controller = new MarkSellableBookSoldController();

    public void markSellableBookSold(SellableBookBean sellableBookBean, AccountBean buyerBean) throws SellableBookException {
        controller.markSellableBookSold(sellableBookBean, buyerBean);
    }

}
