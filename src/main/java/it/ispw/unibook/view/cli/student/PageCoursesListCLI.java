package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageSellableBookCLI;

public class PageCoursesListCLI extends PageManageSellableBookCLI implements PageCLI {

    ManageSellableBookCLI controller = new ManageSellableBookCLI();

    @Override
    public void display() {
        Printer.clear();
        Printer.println("\n--- PAGINA CORSI ---");

        printCoursesList(controller);

        waitForExit();
    }

}
