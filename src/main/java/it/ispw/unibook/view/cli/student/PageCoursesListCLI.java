package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.CoursesListCLI;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

public class PageCoursesListCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    private final CoursesListCLI controller = new CoursesListCLI();

    @Override
    public void display() {
        Printer.clear();
        Printer.println("\n--- PAGINA CORSI ---");

        // Stampa tutti i corsi presenti nel sistema
        printCoursesList(controller);

        // Si attende la pressione del tasto invio per tornare alla home
        waitForExit();

    }

}
