package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.controller.graphics.cli.professor.ManageBookCli;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;
import java.util.List;

public class PageBooksListCLI extends PageManageBookCLI implements PageCLI {

    private final ManageBookCli controller = new ManageBookCli();

    @Override
    public void display() {
        Printer.clear();
        Printer.println("--- PAGINA LIBRI COLLEGATI AL CORSO ---");

        // FIXME exceptions
        try {
            printCoursesList(controller);
        } catch (SessionException e) {
            throw new RuntimeException(e);
        }

        // FIXME exceptions
        int code = requestCourseCode();
        try {
            super.printCourseBooksList(controller, code);
        } catch (CourseException e) {
            throw new RuntimeException(e);
        }

        waitForExit();

    }

    private int requestCourseCode() {

        while (true) {
            try {

                Printer.print("Codice corso: ");
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
