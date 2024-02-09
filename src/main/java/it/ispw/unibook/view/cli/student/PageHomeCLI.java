package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.HomeCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

public class PageHomeCLI extends GenericPageCLI implements PageCLI {

    private static final String MENU_STUDENT_TEXT = """
            --- HOME ---
            
            Seleziona cosa vuoi fare
            [0] Esci
            [1] Visualizza corsi
            [2] Visualizza libri
            [3] Visualizza trattative aperte
            [4] Compra libro
            [5] Inserisci libro in vendita
            [6] Rimuovi libro in vendita
            [7] Segna libro come venduto
            """;

    // Controller grafico relativo alla View
    private final HomeCLI controller = new HomeCLI();

    @Override
    public void display() {

        // Viene stampato il menu
        Printer.println(MENU_STUDENT_TEXT);

        while (true) {

            try {

                int selection = requestInt("Selezione (oppure esc per uscire): ");
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
                Printer.println(MENU_STUDENT_TEXT);

            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            } catch (EscCliException ignored) {
                // L'eccezione può essere ignorata perchè per eseguire il logout l'utente deve immettere 0
            }
        }
    }

}
