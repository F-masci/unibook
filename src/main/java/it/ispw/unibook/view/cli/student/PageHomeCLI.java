package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.HomeCLI;
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

                Printer.print("Azione: ");
                int selection = Integer.parseInt(br.readLine());
                switch (selection) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> controller.showCourses();
                    case 2 -> controller.showBooks();
                    case 3 -> controller.showActiveSellableBooks();
                    case 4 -> controller.showPurchaseBook();
                    case 5 -> controller.showInsertSellableBook();
                    case 6 -> controller.showRemoveSellableBook();
                    case 7 -> controller.showMarkSellableBookSold();
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
            [1] Visualizza corsi
            [2] Visualizza libri
            [3] Visualizza trattative aperte
            [4] Compra libro
            [5] Inserisci libro in vendita
            [6] Rimuovi libro in vendita
            [7] Segna libro come venduto""");
    }

}
