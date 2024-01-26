package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.GenericStudentCLI;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;

public class PageBooksListCLI extends GenericStudentPageCLI implements PageCLI {

    private final GenericStudentCLI controller = new GenericStudentCLI();

    @Override
    public void display() {
        Printer.clear();
        Printer.println("--- PAGINA LIBRI COLLEGATI AL CORSO ---");

        printCoursesList(controller);

        int code = requestCourseCode();
        super.printCourseBooksList(controller, code);

        waitForExit();

    }

}
