package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;

import java.io.IOException;
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

    protected int requestCourseCode() {
        return requestInt("Codice corso: ");
    }
    protected String requestBookCode() {
        return requestBookCode("ISBN libro: ");
    }
    protected int requestSellableBookCode() {
        return requestInt("Codice libro in vendita: ");
    }
    protected int requestAccountCode() {
        return requestInt("Codice acquirente: ");
    }

    protected String requestBookCode(String msg) {
        try {
            Printer.print(msg);
            return br.readLine();
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return null;
    }

    private int requestInt(String msg) {
        while (true) {
            try {
                Printer.print(msg);
                return Integer.parseInt(br.readLine());
            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            }
        }
    }

}
