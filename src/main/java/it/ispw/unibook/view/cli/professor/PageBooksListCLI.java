package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageBookCLI;

import java.io.IOException;

public class PageBooksListCLI extends PageManageBookCLI implements PageCLI {

    private final ManageBookCLI controller = new ManageBookCLI();

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
