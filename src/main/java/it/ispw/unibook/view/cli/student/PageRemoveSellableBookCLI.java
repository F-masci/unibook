package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.controller.graphics.cli.student.InsertSellableCLI;
import it.ispw.unibook.controller.graphics.cli.student.RemoveSellableCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

import java.util.List;

public class PageRemoveSellableBookCLI extends GenericStudentPageCLI implements PageCLI {

    private final RemoveSellableCLI controller = new RemoveSellableCLI();

    @Override
    public void display() {

        try {

            Printer.clear();
            Printer.println("-- PAGINA RIMOZIONE LIBRO IN VENDITA --");

            SellableBooksListBean sellableBooksListBean = new SellableBooksListBean();
            controller.retrieveSellableBooksBySession(sellableBooksListBean);
            List<SellableBookBean> sellableBooks = sellableBooksListBean.getList();

            Printer.println("\n--- LIBRI COLLEGATI ---");

            for (SellableBookBean b : sellableBooks) {
                Printer.println("[" + b.getCode() + "] " + b);
            }

            Printer.println("");

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
            return;
        }

    }
}
