package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.BooksListCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageManageSellableBookCLI;
import it.ispw.unibook.view.cli.PageCLI;

public class PageBooksListCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    // Controller grafico relativo alla View
    private final BooksListCLI controller = new BooksListCLI();

    @Override
    public void display() {
        Printer.clear();
        Printer.println("\n--- PAGINA LIBRI COLLEGATI AL CORSO ---");

        // Stampa tutti i corsi presenti nel sistema
        printCoursesList(controller);

        while(true) {
            try {
                // Richiede il codice di un corso all'utente
                // Se il corso non esiste viene sollevata un'eccezione
                int code = requestCourseCode();
                // Stampa i libri associati al corso
                super.printCourseBooksList(controller, code);

                // Si attende la pressione del tasto invio per tornare alla home
                waitForExit();

                return;

            } catch (CourseException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }

    }

}
