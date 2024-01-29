package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.graphics.cli.professor.InsertBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.exceptions.book.WrongBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageBookCLI;

public class PageInsertBookCLI extends PageManageBookCLI implements PageCLI {

    private static final String ISBN_REQUEST_TEXT = "Inserisci l'isbn del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro: ";
    private static final String TITLE_REQUEST_TEXT = "Inserisci il titolo del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro: ";

    private static final String SUCCESS_MESSAGE_TEXT = "Libro inserito correttamente";

    private final InsertBookCLI controller = new InsertBookCLI();

    private int course;

    @Override
    public void display() {

        Printer.clear();
        Printer.println("--- PAGINA INSERIMENTO LIBRO AD UN CORSO ---");

        while(true) {
            try {
                super.printCoursesList(controller);
                course = super.requestCourseCode();
                insertBookAuto();
                return;
            } catch (SessionException e) {
                showErrorMessage(e);
                return;
            } catch (EscCliException ignored) {
                return;
            } catch (CourseException e) {
                showErrorMessage(e);
            }
        }

    }

    private void insertBookAuto() throws CourseException, EscCliException {

        Printer.clear();
        Printer.println("--- INSERIMENTO AUTOMATICO LIBRO ---");

        while(true) {
            try {

                String isbn = requestBookCode(ISBN_REQUEST_TEXT);
                BookBean bean = new BookBean(isbn);
                controller.getBookInformation(bean);
                Printer.println("Libro trovato: " + bean.getName());

                Printer.print("Le informazioni trovate sono corrette? [S]: ");
                String tmp = requestString();
                if(!tmp.equals("S")) throw new WrongBookException();

                ManageBookBean manageBean = new ManageBookBean(course, bean);
                controller.insertBookInCourse(manageBean);

                Printer.println(SUCCESS_MESSAGE_TEXT);
                waitForExit();

                return;

            } catch (BookException e) {
                Printer.error(e);
                Throwable cause = e.getCause();
                if(cause != null) {

                    if(cause.getClass() == ISBNNotValidException.class || wrongBookHandler()) continue;
                    return;

                }
            }
        }
    }

    private void insertBookManual() throws EscCliException, CourseException {

        Printer.clear();
        Printer.println("--- INSERIMENTO MANUALE LIBRO ---");

        while(true) {
            try {

                String isbn = requestBookCode(ISBN_REQUEST_TEXT);
                String name = requestString(TITLE_REQUEST_TEXT);

                ManageBookBean bean = new ManageBookBean(course, isbn, name);
                controller.insertBookInCourse(bean);

                Printer.println(SUCCESS_MESSAGE_TEXT);
                waitForExit();

                return;

            } catch (BookException e) {
                showErrorMessage(e);
            }
        }

    }

    private boolean wrongBookHandler() throws EscCliException, CourseException {

        Printer.clear();
        Printer.println("Seleziona come procedere");
        Printer.println("""
           [1] Riprova
           [2] Inserisci manualmente""");

        while (true) {
            try {
                Printer.print("Selezione: ");
                int selection = requestInt();
                switch (selection) {
                    case 1 -> {
                        return true;
                    }
                    case 2 -> {
                        insertBookManual();
                        return false;
                    }
                    default -> throw new SelectionNotValidException();
                }

            } catch (SelectionNotValidException exception) {
                showErrorMessage(exception);
            }
        }
    }

}
