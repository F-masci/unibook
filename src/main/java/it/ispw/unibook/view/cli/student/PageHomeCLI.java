package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.HomeCLI;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;

public class PageHomeCLI extends GenericPageCLI implements PageCLI {

    private final HomeCLI controller = new HomeCLI();

    @Override
    public void display() {

        int selection;

        while (true) {

            Printer.clear();

            Printer.println("--- HOME ---");
            Printer.println("Seleziona cosa vuoi fare");

            Printer.println("[0] Esci\n" +
                            "[1] Visualizza corsi\n" +
                            "[2] Visualizza libri");

            while (true) {

                try {

                    Printer.print("Azione: ");
                    selection = Integer.parseInt(br.readLine());
                    switch (selection) {
                        case 0 -> {
                            return;
                        }
                        case 1 -> controller.showCourses();
                        case 2 -> controller.showBooks();
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
    }
}