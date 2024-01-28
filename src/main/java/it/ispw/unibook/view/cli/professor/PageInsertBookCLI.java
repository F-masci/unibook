package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.graphics.cli.professor.InsertBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.book.WrongBookException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageBookCLI;

import java.io.IOException;

public class PageInsertBookCLI extends PageManageBookCLI implements PageCLI {

    private final InsertBookCLI controller = new InsertBookCLI();

    private int course;

    @Override
    public void display() {

        Printer.clear();
        Printer.println("--- INSERIMENTO AUTOMATICO LIBRO ---");

        // FIXME exceptions
        try {
            super.printCoursesList(controller);
        } catch (SessionException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            try {

                Printer.println("Inserisci il codice del corso a cui aggiungere il libro oppure digita esc per tornare indietro");
                Printer.print("Corso: ");
                String line = br.readLine();

                if (line.equals("esc")) return;

                course = Integer.parseInt(line);
                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                Printer.error("Il codice inserito non è un numero");
            }
        }

        insertBookAuto();

    }

    private void insertBookAuto() {
        while(true) {
            try {

                Printer.println("Inserisci l'isbn del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");
                Printer.print("isbn: ");
                String isbn = br.readLine();

                if (isbn.equals("esc")) return;

                BookBean bean = new BookBean(isbn);
                controller.getBookInformation(bean);
                Printer.println("Libro trovato: " + bean.getName());

                Printer.print("Le informazioni trovate sono corrette? [S]: ");
                String tmp = br.readLine();

                if(!tmp.equals("S")) throw new WrongBookException();

                ManageBookBean manageBean = new ManageBookBean(course, bean);
                controller.insertBookInCourse(manageBean);

                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                Printer.error("L'isbn inserito non è un numero");
            } catch (BookException | CourseException e) {
                Throwable cause = e.getCause();
                if(cause != null) {
                    if(cause.getClass() == BookNotFoundException.class) Printer.println("Il libro non è stato trovato");
                    if(cause.getClass() == BookNotFoundException.class || e.getClass() == WrongBookException.class && wrongBookHandler()) continue;
                } else {
                    Printer.error(e);
                }
                break;
            }
        }
    }

    private boolean wrongBookHandler() {

        Printer.println("Seleziona come procedere");
        int selection;

        Printer.clear();

        Printer.println("""
           [1] Riprova
           [2] Inserisci manualmente""");

        while (true) {

            try {

                Printer.print("Azione: ");
                selection = Integer.parseInt(br.readLine());
                switch (selection) {
                    case 1 -> {
                        return true;
                    }
                    case 2 -> insertBookManual();
                    default -> throw new SelectionNotValidException();
                }

                return false;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            }
        }
    }

    private void insertBookManual() {

        Printer.clear();

        Printer.println("--- INSERIMENTO MANUALE LIBRO ---");

        String isbn;
        String name;

        while(true) {
            try {

                Printer.println("Inserisci l'isbn del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");
                Printer.print("isbn: ");
                isbn = br.readLine();

                if (isbn.equals("esc")) return;

                Printer.println("Inserisci il titolo del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");
                Printer.print("Titolo: ");
                name = br.readLine();

                if (name.equals("esc")) return;

                ManageBookBean bean = new ManageBookBean(course, isbn, name);
                controller.insertBookInCourse(bean);
                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (BookException | CourseException e) {
                Printer.error(e);
            }
        }

    }

}
