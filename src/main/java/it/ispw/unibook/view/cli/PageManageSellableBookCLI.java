package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;

import java.util.List;

public class PageManageSellableBookCLI extends PageManageBookCLI {

    protected PageManageSellableBookCLI() {}

    protected void printCoursesList(ManageSellableBookCLI controller) {
        CoursesListBean bean = new CoursesListBean();
        controller.retrieveCourses(bean);
        List<CourseBean> courses = bean.getList();

        Printer.println("\n--- CORSI DISPONIBILI ---");
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c);
        }
        Printer.println("");
    }

    protected void printSellableBooksList(ManageSellableBookCLI controller) throws SessionException {
        SellableBooksListBean bean = new SellableBooksListBean();
        controller.retrieveSellableBooksBySession(bean);
        List<SellableBookBean> sellableBooks = bean.getList();

        Printer.println("\n--- LIBRI IN VENDITA ---");

        for (SellableBookBean b : sellableBooks) {
            Printer.println("[" + b.getCode() + "] " + b);
        }

        Printer.println("");

    }

    protected void printSellableBookActiveNegotiationsList(ManageSellableBookCLI controller, int sellableBookCode) throws BookException {
        SellableBookBean sellableBookBean = new SellableBookBean(sellableBookCode);
        AccountsListBean accountsListBean = controller.retrieveActiveNegotiationBySellableBook(sellableBookBean);
        List<AccountBean> accounts = accountsListBean.getList();

        Printer.println("\n--- TRATTATIVE IN CORSO ---");

        for (AccountBean a : accounts) {
            Printer.println("[" + a.getCode() + "] " + a);
        }

        Printer.println("");

    }

    protected void printActiveNegotiationsList(ManageSellableBookCLI controller) throws SessionException {
        SellableBooksListBean bean = new SellableBooksListBean();
        controller.retrieveSellableBooksBySessionActiveNegotiation(bean);
        List<SellableBookBean> sellableBooks = bean.getList();

        Printer.println("\n--- LIBRI IN VENDITA ---");

        for (SellableBookBean b : sellableBooks) {
            Printer.println("[" + b.getCode() + "] " + b);
        }

        Printer.println("");

    }

    // FIXME
    protected int requestCourseCode() {
        try {
            return requestInt("Codice corso: ");
        } catch (EscCliException e) {
            throw new RuntimeException(e);
        }
    }

    // FIXME
    protected int requestSellableBookCode() {
        try {
            return requestInt("Codice libro in vendita: ");
        } catch (EscCliException e) {
            throw new RuntimeException(e);
        }
    }


    protected int requestAccountCode() {
        try {
            return requestInt("Codice acquirente: ");
        } catch (EscCliException e) {
            throw new RuntimeException(e);
        }
    }

}
