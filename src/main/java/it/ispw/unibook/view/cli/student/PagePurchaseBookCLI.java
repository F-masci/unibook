package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.controller.graphics.cli.student.PurchaseBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageManageSellableBookCLI;
import it.ispw.unibook.view.cli.PageCLI;
import org.jetbrains.annotations.NotNull;

public class PagePurchaseBookCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Acquisto avviato correttamente";

    // Controller grafico relativo alla View
    PurchaseBookCLI controller = new PurchaseBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA ACQUISTO LIBRO ---");
        Printer.println("Seleziona come vuoi cercare");
        Printer.println("""
                [1] Ricerca globale
                [2] Cerca nel corso""");

        while (true) {
            try {
                int selection = requestInt("Selezione (oppure esc per tornare alla home): ");
                switch (selection) {
                    case 1 -> showGlobalSearch();
                    case 2 -> showCourseSearch();
                    default -> throw new SelectionNotValidException();
                }
                return;
            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }

    }

    /**
     * Ricerca il libro in vendita tramite codice del libro (ISBN)
     */
    private void showGlobalSearch() {
        while(true) {
            try {
                // Richiede all'utente il codice del libro
                String isbn = requestBookCode();
                // Viene istanziato il bean relativo al libro scelto
                BookBean bean = new BookBean(isbn);
                // Viene chiesto all'utente di scegliere quale libro acquistare tra quelli con quel codice
                purchaseBook(controller.retrieveSellableBooksByIsbn(bean));
                return;
            } catch (NegotiationException | SessionException | BookException | SellableBookException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }
    }

    /**
     * Ricerca il libro in vendita in uno specifico corso
     */
    private void showCourseSearch() {
        while(true) {
            try {
                // Stampa la lista dei corsi
                super.printCoursesList(controller);
                // Richiede all'utente il codice di un corso
                int course = requestCourseCode();
                // Viene istanziato il bean relativo al corso scelto
                CourseBean courseBean = new CourseBean(course);
                // Viene chiesto all'utente di scegliere quale libro acquistare tra quelli del corso selezionato
                purchaseBook(controller.retrieveSellableBooksByCourse(courseBean));
                return;
            } catch (NegotiationException | SessionException | CourseException | SellableBookException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }
    }

    /**
     * Esegue l'acquisto di un libro dopo averlo cercato
     * @param sellableBooksListBean Lista dei libri da poter comprare
     * @throws NegotiationException Viene sollevata se l'utente ha già acquistato questo libro
     * @throws SessionException Viene sollevata se la sessione non è stata trovata
     * @throws EscCliException Viene sollevata se l'utente immette esc come input per tornare alla home
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato
     * @throws BookException Viene sollevata se
     */
    private void purchaseBook(@NotNull SellableBooksListBean sellableBooksListBean) throws NegotiationException, SessionException, EscCliException, SellableBookException {
        try {
            // Stampa i libri in vendita
            printSellableBooksListBean(sellableBooksListBean, "LIBRI COLLEGATI");
            // Richiede all'utente un libro in vendita
            int code = requestSellableBookCode();
            // Viene istanziato il bean contenete il libro da acquistare
            SellableBookBean purchaseBean = new SellableBookBean(code);
            // Viene inviato il bean contenete il libro da acquistare al controller grafico
            controller.purchaseBook(purchaseBean);

            // Se non vengono sollevate eccezioni l'operazione è stata completata correttamente e viene stampato il messaggio di conferma
            Printer.println(SUCCESS_MESSAGE_TEXT);
            // Si attende la pressione del tasto invio per tornare alla home
            waitForExit();

        } catch(BookException ignored) {
            // Può essere ignorata poiché viene sollevata solo se l'isbn del libro
            // non è valido, ma in questo caso viene inserito solo il codice de libro in vendita
        }
    }

}
