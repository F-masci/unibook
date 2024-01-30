package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.graphics.cli.student.InsertSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageManageSellableBookCLI;
import it.ispw.unibook.view.cli.PageCLI;

public class PageInsertSellableBookCLI extends GenericPageManageSellableBookCLI implements PageCLI {

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro inserito correttamente in vendita";

    // Controller grafico relativo alla View
    private final InsertSellableBookCLI controller = new InsertSellableBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA INSERIMENTO LIBRO IN VENDITA ---");

        int courseCode;

        try {
            // Stampa tutti i corsi presenti nel sistema
            super.printCoursesList(controller);
            // Richiede il codice di un corso all'utente
            // Se il corso non esiste viene sollevata un'eccezione
            courseCode = requestCourseCode();
            // Stampa i libri associati al corso
            super.printCourseBooksList(controller, courseCode);
        } catch (CourseException e) {
            showErrorMessage(e);
            return;
        } catch (EscCliException e) {
            return;
        }

        while(true) {
            try {
                // Richiede all'utente il codice di un libro (ISBN)
                String isbn = super.requestBookCode();
                // Istanzia il bean relativo al libro
                // Se il codice inserito non ha un formato valido viene sollevata un'eccezione
                BookBean bookBean = new BookBean(isbn);

                // Richiede il prezzo di vendita del libro all'utente
                float price = requestFloat("Prezzo di vendita: ");
                // Istanzia il bean per salvare il nuovo libro in vendita
                SellableBookBean sellableBookBean = new SellableBookBean(courseCode, bookBean, price);

                // Viene inviato il bean contenete i dati del libro, del corso e il prezzo al controller applicativo
                controller.insertSellableBook(sellableBookBean);

                // Se non vengono sollevate eccezioni l'operazione è stata completata correttamente e viene stampato il messaggio di conferma
                Printer.println(SUCCESS_MESSAGE_TEXT);
                // Si attende la pressione del tasto invio per tornare alla home
                waitForExit();

                return;

            } catch(BookException | SessionException | CourseException | SellableBookException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }

    }
}
