package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.professor.HomeCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

public class PageHomeCLI extends GenericPageCLI implements PageCLI {

    private static final String MENU_PROFESOR_TEXT = """
            --- HOME ---
            
            Seleziona cosa vuoi fare
            [0] Esci
            [1] Visualizza libri di un corso
            [2] Inserisci libro ad un corso
            [3] Elimina libro da un corso
            """;

    // Controller grafico relativo alla View
    private final HomeCLI controller = new HomeCLI();

    @Override
    public void display() {

        // Viene stampato il menu
        Printer.println(MENU_PROFESOR_TEXT);

        while (true) {

            try {

                // Si chiede all'utente di scegliere cosa fare dal menù
                int selection = requestInt("Selezione (oppure esc per uscire): ");
                switch (selection) {
                    case 0 -> { return; }
                    case 1 -> controller.showBooks();
                    case 2 -> controller.addBook();
                    case 3 -> controller.deleteBook();
                    default -> throw new SelectionNotValidException();
                }

                // Viene ristampato il menu
                Printer.println(MENU_PROFESOR_TEXT);

            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }
    }

}
