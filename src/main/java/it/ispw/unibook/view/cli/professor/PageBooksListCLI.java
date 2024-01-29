package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageBookCLI;

public class PageBooksListCLI extends PageManageBookCLI implements PageCLI {

    private final ManageBookCLI controller = new ManageBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA LIBRI COLLEGATI AD UN CORSO ---");

        try {
            super.printCoursesList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                int code = requestCourseCode();
                super.printCourseBooksList(controller, code);

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
