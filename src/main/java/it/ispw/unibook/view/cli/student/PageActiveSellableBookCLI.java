package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.controller.graphics.cli.ManageSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageSellableBookCLI;

import java.io.IOException;

public class PageActiveSellableBookCLI extends PageManageSellableBookCLI implements PageCLI {

    private final ManageSellableBookCLI controller = new ManageSellableBookCLI();

    @Override
    public void display() {

        int selection;

        while (true) {

            Printer.clear();

            Printer.println("--- PAGINA TRATTATIVE ATTIVE ---");
            Printer.println("Seleziona quali vuoi visualizzare");

            Printer.println("""
                [0] Esci
                [1] I tuoi libri in vendita
                [2] Libri che stai acquistando""");

            while (true) {

                try {

                    Printer.print("Azione: ");
                    selection = Integer.parseInt(br.readLine());
                    switch (selection) {
                        case 0 -> {
                            return;
                        }
                        case 1 -> showOwnSellableBooks();
                        case 2 -> showActiveNegotiation();
                        default -> throw new SelectionNotValidException();
                    }

                    return;

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

    private void showOwnSellableBooks() {
        try {
            super.printSellableBooksList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                int sellableBook = super.requestSellableBookCode();
                super.printSellableBookActiveNegotiationsList(controller, sellableBook);

                waitForExit();

                return;
            } catch (BookException e) {
                showErrorMessage(e);
            }
        }
    }

    public void showActiveNegotiation() {
        try {
            super.printActiveNegotiationsList(controller);
            waitForExit();
        } catch (SessionException e) {
            showErrorMessage(e);
        }
    }

}
