package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.professor.InsertBookCLI;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.exceptions.book.WrongBookException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericProfessorPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PageInsertBookCLI extends GenericProfessorPageCLI implements PageCLI {

    private final InsertBookCLI controller = new InsertBookCLI();

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int course;

    @Override
    public void display() {

        Printer.clear();

        Printer.println("--- INSERIMENTO AUTOMATICO LIBRO ---");

        printCoursesList();

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

        String ISBN;

        while(true) {
            try {

                Printer.println("Inserisci l'ISBN del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");
                Printer.print("ISBN: ");
                ISBN = br.readLine();

                if (ISBN.equals("esc")) return;

                String book = controller.getBookInformation(ISBN);
                Printer.println("Libro trovato: " + book);

                Printer.print("Le informazioni trovate sono corrette? [S]: ");
                String tmp = br.readLine();

                if(tmp.equals("S")) {
                    controller.insertBook(course, ISBN, book);
                    break;
                } else {
                    throw new WrongBookException();
                }

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (ISBNNotValidException e) {
                Printer.error(e);
            } catch (NumberFormatException e) {
                Printer.error("L'ISBN inserito non è un numero");
            } catch (BookNotFoundException e) {
                Printer.println("Il libro non è stato trovato");
                wrongBookHandler();
                break;
            } catch (WrongBookException e) {
                wrongBookHandler();
                break;
            }
        }
    }

    private void wrongBookHandler() {

        Printer.println("Seleziona come procedere");
        int selection;

        Printer.clear();

        Printer.println("[1] Riprova\n" +
                        "[2] Inserisci manualmente");

        while (true) {

            try {

                Printer.print("Azione: ");
                selection = Integer.parseInt(br.readLine());
                switch (selection) {
                    case 1 -> insertBookAuto();
                    case 2 -> insertBookManual();
                    default -> throw new SelectionNotValidException();
                }

                break;

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

        String ISBN, name;

        while(true) {
            try {

                Printer.println("Inserisci l'ISBN del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");
                Printer.print("ISBN: ");
                ISBN = br.readLine();

                if (ISBN.equals("esc")) return;

                Printer.println("Inserisci il titolo del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");
                Printer.print("Titolo: ");
                name = br.readLine();

                if (name.equals("esc")) return;

                controller.insertBook(course, ISBN, name);
                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (ISBNNotValidException e) {
                Printer.error(e);
            }
        }

    }

}
