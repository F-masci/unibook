package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.graphics.cli.professor.RemoveBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.GenericPageManageBookCLI;

public class PageRemoveBookCLI extends GenericPageManageBookCLI implements PageCLI {

    // Messaggio per richiedere il codice del corso all'utente
    private static final String COURSE_CODE_REQUEST_TEXT = "Inserisci il codice del corso a cui rimuovere il libro oppure digita esc per tornare indietro: ";
    // Messaggio per richiedere l'ISBN del libro all'utente
    private static final String ISBN_REQUEST_TEXT = "Inserisci l'isbn del libro da rimuovere oppure digita esc per tornare indietro: ";

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro inserito correttamente";

    // Controller grafico relativo alla View
    private final RemoveBookCLI controller = new RemoveBookCLI();


    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA RIMOZIONE LIBRO DA UN CORSO ---");

        try {
            // Stampa la lista dei corsi collegati all'utente loggato
            super.printSessionCoursesList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            System.exit(-1);
        }

        while(true) {
            try {

                // Richiede il codice del corso all'utente
                int courseCode = requestCourseCode(COURSE_CODE_REQUEST_TEXT);
                // Crea il bean da inviare al controller applicativo
                CourseBean courseBean = new CourseBean(courseCode);

                // Viene stampata la lista dei libri associati al corso
                super.printCourseBooksList(controller, courseCode);
                // Richiede il codice del libro all'utente
                String isbn = requestBookCode(ISBN_REQUEST_TEXT);
                // Crea il bean da inviare al controller applicativo
                BookBean bookBean = new BookBean(isbn);

                // Vengono inviati i bean contenenti i dati del libro e del corso al controller grafico
                controller.removeBookFromCourse(courseBean, bookBean);

                // Se non vengono sollevate eccezioni l'operazione è stata completata correttamente e viene stampato il messaggio di conferma
                Printer.println(SUCCESS_MESSAGE_TEXT);
                // Si attende un qualsiasi pulsante premuto per tornare alla home
                waitForExit();

                return;

            } catch (BookException | CourseException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }

    }
}
