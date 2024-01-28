package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.graphics.cli.student.RemoveSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageSellableBookCLI;

public class PageRemoveSellableBookCLI extends PageManageSellableBookCLI implements PageCLI {

    private final RemoveSellableBookCLI controller = new RemoveSellableBookCLI();

    @Override
    public void display() {

        try {

            Printer.clear();
            Printer.println("-- PAGINA RIMOZIONE LIBRO IN VENDITA --");

            super.printSellableBooksList(controller);

            while(true) {
                try {
                    int code = requestSellableBookCode();
                    SellableBookBean sellableBookBean = new SellableBookBean(code);
                    controller.removeSellableBook(sellableBookBean);

                    Printer.println("Libro eliminato correttamente");

                    waitForExit();

                    return;

                } catch (BookException | SessionException e) {
                    showErrorMessage(e);
                }
            }
        } catch (SessionException e) {
            showErrorMessage(e);
        }

    }
}
