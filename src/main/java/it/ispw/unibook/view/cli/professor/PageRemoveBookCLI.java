package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.graphics.cli.professor.RemoveBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageBookCLI;

import java.io.IOException;

public class PageRemoveBookCLI extends PageManageBookCLI implements PageCLI {

    private final RemoveBookCLI controller = new RemoveBookCLI();


    @Override
    public void display() {

        Printer.clear();
        Printer.println("--- RIMOZIONE LIBRO ---");

        // FIXME exceptions
        try {
            super.printCoursesList(controller);
        } catch (SessionException e) {
            throw new RuntimeException(e);
        }

        String isbn;
        int course;

        while(true) {
            try {

                Printer.println("Inserisci il codice del corso a cui rimuovere il libro oppure digita esc per tornare indietro");
                Printer.print("Corso: ");
                String line = br.readLine();

                if (line.equals("esc")) return;

                course = Integer.parseInt(line);

                Printer.println("Inserisci l'isbn del libro da rimuovere oppure digita esc per tornare indietro");
                Printer.print("Libro: ");
                isbn = br.readLine();

                if (isbn.equals("esc")) return;

                ManageBookBean bean = new ManageBookBean(course, isbn);
                controller.removeBookFromCourse(bean);
                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                Printer.error("Il codice inserito non è un numero");
            } catch (BookException e) {
                Printer.error(e);
            }
        }

    }
}
