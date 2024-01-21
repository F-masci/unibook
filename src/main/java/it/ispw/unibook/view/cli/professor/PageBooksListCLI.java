package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.controller.graphics.cli.professor.BooksListCLI;
import it.ispw.unibook.controller.graphics.cli.professor.InsertBookCLI;
import it.ispw.unibook.controller.graphics.cli.professor.ManageBookCli;
import it.ispw.unibook.controller.graphics.cli.professor.RemoveBookCLI;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;
import java.util.List;

public class PageBooksListCLI extends PageManageBookCLI implements PageCLI {

    private final BooksListCLI controller;

    public PageBooksListCLI() {
        super(new BooksListCLI());
        controller = (BooksListCLI) super.getController();
    }

    @Override
    public void display() {
        Printer.clear();

        Printer.println("--- PAGINA LIBRI COLLEGATI AL CORSO ---");

        printCoursesList();

        int code = requestCourseCode();
        showBooks(code);

    }

    private int requestCourseCode() {

        int code;

        while (true) {
            try {

                Printer.print("Codice corso: ");
                code = Integer.parseInt(br.readLine());

                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            }
        }

        return code;

    }

    private void showBooks(int code) {

        BooksListBean bean = new BooksListBean(code);
        controller.getBooks(bean);
        List<BookBean> books = bean.getList();

        Printer.println("--- LIBRI COLLEGATI ---");

        for (BookBean b : books) {
            Printer.println("[" + b.getISBN() + "] " + b);
        }

        Printer.println("");

    }

    private void waitForExit() {
        Printer.println("Premi un qualsisi tasto per tornare alla home");
        try {
            br.readLine();
        } catch (IOException e) {
            Printer.error("Errore durante la lettura dell'input");
            System.exit(-1);
        }
    }

}
