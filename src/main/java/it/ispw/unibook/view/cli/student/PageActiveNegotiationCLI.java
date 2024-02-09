package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.student.ActiveNegotiationCLI;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

public class PageActiveNegotiationCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    // Controller grafico relativo alla View
    private final ActiveNegotiationCLI controller = new ActiveNegotiationCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA TRATTATIVE ATTIVE ---");
        Printer.println("Seleziona quali vuoi visualizzare");
        Printer.println("""
            [1] I tuoi libri in vendita
            [2] Libri che stai acquistando""");

        while (true) {
            try {
                int selection = requestInt("Selezione (oppure esc per tornare alla home): ");
                switch (selection) {
                    case 1 -> showOwnSellableBooks();
                    case 2 -> showActiveNegotiation();
                    default -> throw new SelectionNotValidException();
                }
                return;
            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            } catch (EscCliException ignored) {
                return;
            }
        }
    }

    /**
     * Mostra la lista delle trattative attive per un libro in vendita dell'utente loggato
     */
    private void showOwnSellableBooks() {
        try {
            super.printSellableBooksList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                // Richiede il codice di un libro in vendita all'utente
                // Se il libro non viene trovato viene sollevata un'eccezione
                int sellableBook = super.requestSellableBookCode();
                // Stampa le trattative attive per quel libro in vendita
                super.printSellableBookActiveNegotiationsList(controller, sellableBook);

                // Si attende la pressione del tasto invio per tornare alla home
                waitForExit();

                return;
            } catch (SellableBookException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }
    }

    /**
     * Mostra la lista dei libri che il cliente loggato sta acquistando
     */
    public void showActiveNegotiation() {
        try {
            super.printActiveNegotiationsList(controller);
            waitForExit();
        } catch (SessionException e) {
            showErrorMessage(e);
        }
    }

}
