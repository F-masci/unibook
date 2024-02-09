package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.graphics.cli.student.MarkSellableBookSoldCLI;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

public class PageMarkSellableBookSoldCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro segnato correttamente come venduto";

    // Controller grafico relativo alla View
    private final MarkSellableBookSoldCLI controller = new MarkSellableBookSoldCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA SEGNA LIBRO COME VENDUTO ---");

        while(true) {
            try {
                // Stampa i libri in vendita dell'utente loggato
                super.printSellableBooksList(controller);
                // Richiede all'utente il codice di un libro in vedita
                int sellableBook = super.requestSellableBookCode();
                // Stampa tutti gli studenti intenzionati a comprare il libro selezionato
                super.printSellableBookActiveNegotiationsList(controller, sellableBook);
                // Richiede all'utente il codice dello studente che ha effettivamente comprato il libro
                int buyer = super.requestAccountCode();

                // Istanzia il bean per salvare il libro in vendita
                SellableBookBean sellableBookBean = new SellableBookBean(sellableBook);
                // Istanzia il bean per salvare lo studente che ha effettivamente comprato il libro
                AccountBean buyerBean = new AccountBean(buyer);

                // Vengono inviati i bean contenenti il libro in vendita e l'acquirente al controller grafico
                controller.markSellableBookSold(sellableBookBean, buyerBean);

                // Se non vengono sollevate eccezioni l'operazione è stata effettuata correttamente e viene stampato il messaggio di conferma
                Printer.println(SUCCESS_MESSAGE_TEXT);
                // Si attende la pressione del tasto invio per tornare alla home
                waitForExit();

                return;

            } catch (BookException | SellableBookException | SessionException | AccountNotFoundException e) {
                showErrorMessage(e);
            } catch(EscCliException e) {
                return;
            }
        }

    }
}
