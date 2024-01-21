package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.graphics.cli.professor.InsertBookCLI;
import it.ispw.unibook.controller.graphics.cli.professor.RemoveBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;

public class PageRemoveBookCLI extends PageManageBookCLI implements PageCLI {

    private final RemoveBookCLI controller;

    public PageRemoveBookCLI() {
        super(new RemoveBookCLI());
        controller = (RemoveBookCLI) super.getController();
    }

    @Override
    public void display() {

        Printer.clear();

        Printer.println("--- RIMOZIONE LIBRO ---");

        printCoursesList();

        String ISBN;
        int course;

        while(true) {
            try {

                Printer.println("Inserisci il codice del corso a cui rimuovere il libro oppure digita esc per tornare indietro");
                Printer.print("Corso: ");
                String line = br.readLine();

                if (line.equals("esc")) return;

                course = Integer.parseInt(line);

                Printer.println("Inserisci l'ISBN del libro da rimuovere oppure digita esc per tornare indietro");
                Printer.print("Libro: ");
                ISBN = br.readLine();

                if (ISBN.equals("esc")) return;

                ManageBookBean bean = new ManageBookBean(course, ISBN);
                controller.removeBook(bean);
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
