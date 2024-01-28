package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.graphics.cli.student.MarkSellableBookSoldCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageSellableBookCLI;

public class PageMarkSellableBookSoldCLI extends PageManageSellableBookCLI implements PageCLI {

    MarkSellableBookSoldCLI controller = new MarkSellableBookSoldCLI();

    @Override
    public void display() {
        try {
            super.printSellableBooksList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                int sellableBook = super.requestSellableBookCode();
                super.printSellableBookActiveNegotiationsList(controller, sellableBook);

                int buyer = super.requestAccountCode();

                SellableBookBean sellableBookBean = new SellableBookBean(sellableBook);
                AccountBean buyerBean = new AccountBean(buyer);

                controller.markSellableBookSold(sellableBookBean, buyerBean);
                Printer.println("Libro segnato correttamente come venduto");

                waitForExit();

                return;
            } catch (BookException e) {
                showErrorMessage(e);
            }
        }

    }
}
