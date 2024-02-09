package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.graphics.cli.student.RemoveSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

public class PageRemoveSellableBookCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro rimosso correttamente";

    // Controller grafico relativo alla View
    private final RemoveSellableBookCLI controller = new RemoveSellableBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("-- PAGINA RIMOZIONE LIBRO IN VENDITA --");

        try {
            // Stampa la lista dei libri in vendita dell'utente loggato
            super.printSellableBooksList(controller);
        } catch (SessionException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                // Richiede il codice di un libro in vendita
                int code = requestSellableBookCode();
                // Istanzia il bean per rimuovere il libro in vendita
                SellableBookBean sellableBookBean = new SellableBookBean(code);
                // Viene inviato il bean contenete i dati del libro, del corso e il prezzo al controller applicativo
                controller.removeSellableBook(sellableBookBean);

                // Se non vengono sollevate eccezioni l'operazione è stata effettuata correttamente e viene stampato il messaggio di conferma
                Printer.println(SUCCESS_MESSAGE_TEXT);
                // Si attende la pressione del tasto invio per tornare alla home
                waitForExit();

                return;

            } catch (BookException | SessionException | SellableBookException | CourseException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }

    }
}
