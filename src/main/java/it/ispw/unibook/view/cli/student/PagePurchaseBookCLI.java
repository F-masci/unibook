package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.controller.graphics.cli.student.PurchaseBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageSellableBookCLI;

import java.io.IOException;
import java.util.List;

public class PagePurchaseBookCLI extends PageManageSellableBookCLI implements PageCLI {

    PurchaseBookCLI controller = new PurchaseBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("-- PAGINA ACQUISTO LIBRO --");

        int selection;

        Printer.println("""
                [0] Esci
                [1] Ricerca globale
                [2] Cerca nel corso""");

        while (true) {

            try {

                Printer.print("Azione: ");
                selection = Integer.parseInt(br.readLine());
                switch (selection) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> showGlobalSearch();
                    case 2 -> showCourseSearch();
                    default -> throw new SelectionNotValidException();
                }

                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            }
        }

    }

    private void showGlobalSearch() {
        while(true) {
            try {
                String isbn = requestBookCode();
                BookBean bean = new BookBean(isbn);
                purchaseBook(controller.retrieveSellableBooksByIsbn(bean));
                break;
            } catch (BookException | NegotiationException | SessionException e) {
                showErrorMessage(e);
            }
        }
    }

    private void showCourseSearch() {
        while(true) {
            try {
                super.printCoursesList(controller);
                int course = requestCourseCode();
                CourseBean courseBean = new CourseBean(course);
                purchaseBook(controller.retrieveSellableBooksByCourse(courseBean));
                break;
            } catch (BookException | NegotiationException | SessionException e) {
                showErrorMessage(e);
            }
        }
    }

    private void purchaseBook(SellableBooksListBean sellableBooksListBean) throws BookException, NegotiationException, SessionException {

        List<SellableBookBean> sellableBooks = sellableBooksListBean.getList();
        Printer.println("\n--- LIBRI COLLEGATI ---");

        for (SellableBookBean b : sellableBooks) {
            Printer.println("[" + b.getCode() + "] " + b);
        }

        int code = requestSellableBookCode();
        SellableBookBean purchaseBean = new SellableBookBean(code);
        controller.purchaseBook(purchaseBean);

        Printer.println("Trattativa iniziata correttamente");

        waitForExit();

    }

}
