package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.professor.HomeCLI;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericProfessorPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;

public class PageHomeCLI extends GenericProfessorPageCLI implements PageCLI {

    private final HomeCLI controller = new HomeCLI();

    @Override
    public void display() {

        int selection;

        while(true) {

            Printer.clear();

            Printer.println("--- HOME ---");
            Printer.println("Seleziona cosa vuoi fare");

            Printer.println("[0] Esci\n" +
                            "[1] Visualizza libri di un corso\n" +
                            "[2] Inserisci libro ad un corso\n" +
                            "[3] Elimina libro da un corso");

            while (true) {

                try {

                    Printer.print("Azione: ");
                    selection = Integer.parseInt(br.readLine());
                    switch (selection) {
                        case 0 -> {
                            return;
                        }
                        case 1 -> controller.showBooks();
                        case 2 -> controller.addBook();
                        case 3 -> controller.deleteBook();
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
