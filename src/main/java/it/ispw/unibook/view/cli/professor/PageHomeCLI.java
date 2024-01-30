package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.professor.HomeCLI;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;

public class PageHomeCLI extends GenericPageCLI implements PageCLI {

    // Controller grafico relativo alla View
    private final HomeCLI controller = new HomeCLI();

    @Override
    public void display() {

        // Viene stampato il menu
        printMenu();

        while (true) {

            try {

                // Si chiede all'utente di scegliere cosa fare dal menù
                Printer.print("Selezione: ");
                int selection = Integer.parseInt(br.readLine());
                switch (selection) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> controller.showBooks();
                    case 2 -> controller.addBook();
                    case 3 -> controller.deleteBook();
                    default -> throw new SelectionNotValidException();
                }

                // Viene ristampato il menu
                printMenu();

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

    /**
     * Stampa il menu delle possibili scelte
     */
    public void printMenu() {
        Printer.clear();
        Printer.println("--- HOME ---\n");
        Printer.println("Seleziona cosa vuoi fare");
        Printer.println("""
            [0] Esci
            [1] Visualizza libri di un corso
            [2] Inserisci libro ad un corso
            [3] Elimina libro da un corso""");
    }

}
