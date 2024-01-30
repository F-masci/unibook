package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.professor.ListBookCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageManageBookCLI;
import it.ispw.unibook.view.cli.PageCLI;

public class PageBooksListCLI extends GenericPageManageBookCLI implements PageCLI {

    // Controller grafico relativo alla View
    private final ListBookCLI controller = new ListBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA LIBRI COLLEGATI AD UN CORSO ---");

        try {
            // Viene stampata la lista dei corsi relativa all'utente loggato
            super.printCoursesList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                // Viene richiesto il codice del corso di cui stampare la lista dei libri
                int code = requestCourseCode();
                // Viene stampata la lista dei libri associati al corso
                super.printCourseBooksList(controller, code);

                // Si attende un qualsiasi pulsante premuto per tornare alla home
                waitForExit();
                return;

            } catch (EscCliException e) {
                return;
            } catch (CourseException e) {
                showErrorMessage(e);
            }
        }

    }

}
