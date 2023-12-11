package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.controller.graphics.cli.professor.BooksListCLI;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PageBooksListCLI extends GenericPageCLI implements PageCLI {

    private final BooksListCLI controller = new BooksListCLI();

    @Override
    public void init() {
        Printer.clear();

        Printer.println("--- LIBRI ---");

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

        Printer.println("Questa è la lista dei libri collegati al corso scelto");

        for (BookBean b : books) {
            Printer.println("[" + b.getISBN() + "] " + b.getName());
        }


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
