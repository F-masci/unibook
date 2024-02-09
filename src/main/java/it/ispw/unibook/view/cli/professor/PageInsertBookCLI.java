package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.graphics.cli.professor.InsertBookCLI;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.ISBNNotValidException;
import it.ispw.unibook.exceptions.book.WrongBookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;

public class PageInsertBookCLI extends GenericPageManageBookCLI implements PageCLI {

    // Messaggio per richiedere l'ISBN del libro all'utente
    private static final String ISBN_REQUEST_TEXT = "Inserisci l'isbn del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro: ";
    // Messaggio per richiedere il titolo del libro all'utente
    private static final String TITLE_REQUEST_TEXT = "Inserisci il titolo del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro: ";

    // Messaggio di conferma dell'operazione
    private static final String SUCCESS_MESSAGE_TEXT = "Libro inserito correttamente";

    // Controller grafico relativo alla View
    private final InsertBookCLI controller = new InsertBookCLI();

    // Corso selezionato dall'utente per inserire il libro
    private CourseBean courseBean;

    @Override
    public void display() {

        Printer.clear();
        Printer.println("\n--- PAGINA INSERIMENTO LIBRO AD UN CORSO ---");

        while(true) {
            try {
                // Stampa la lista dei corsi collegati all'utente loggato
                super.printSessionCoursesList(controller);
                // Richiede il codice di un corso e lo inserisce nel Bean
                courseBean = new CourseBean(super.requestCourseCode());
                // Avvia l'inserimento automatico di un libro
                insertBookAuto();
                return;
            } catch (SessionException e) {
                showErrorMessage(e);
                return;
            } catch (EscCliException ignored) {
                return;
            } catch (CourseException e) {
                showErrorMessage(e);
            }
        }

    }

    /**
     * Inserimento automatico del libro.
     * Il libro viene cercato tramite ISBN all'interno della libreria.
     * In caso non venga trovato i dati relativi potranno essere inseriti manualmente
     * @throws CourseException Viene sollevata nel caso in cui il corso non sia stato trovato
     * @throws EscCliException Viene sollevata nel caso in cui l'utente digita esc per tornare alla home
     */
    private void insertBookAuto() throws CourseException, EscCliException {

        Printer.clear();
        Printer.println("--- INSERIMENTO AUTOMATICO LIBRO ---");

        while(true) {
            try {
                // Richiede il codice del libro all'utente
                String isbn = requestBookCode(ISBN_REQUEST_TEXT);
                // Crea il bean da inviare al controller applicativo
                BookBean bookBean = new BookBean(isbn);
                // Vengono richiesti i dati del libro dalla libreria
                // Il controller applicativo restituirà nel bean i dati relativi al libro
                controller.getBookInformation(bookBean);

                // Vengono stampati i dati relativi al libro trovato
                Printer.println("Libro trovato: " + bookBean.getName());
                // Viene chiesto all'utente di confermare i dati
                Printer.print("Le informazioni trovate sono corrette? [S]: ");
                String tmp = requestString();
                // In caso i dati non siano confermati viene sollevata l'eccezione
                // per chiedere all'utente come continuare
                if(!tmp.equals("S")) throw new WrongBookException();

                // Vengono inviati i bean contenenti i dati del libro e del corso al controller grafico
                controller.insertBookInCourse(courseBean, bookBean);

                // Se non vengono sollevate eccezioni l'operazione è stata completata correttamente e viene stampato il messaggio di conferma
                Printer.println(SUCCESS_MESSAGE_TEXT);
                // Si attende la pressione del tasto invio per tornare alla home
                waitForExit();

                return;

            } catch (BookException e) {
                Printer.error(e);
                Throwable cause = e.getCause();
                if(cause != null) {

                    // In caso di errore, se l'errore non è stato causato dalla formattazione errata dell'ISBN
                    // viene eseguita la gestione di logica dell'errore tramite la relativa funzione
                    if(cause.getClass() == ISBNNotValidException.class || wrongBookHandler()) continue;
                    // A seguito della gestione dell'errore il libro è stato inserito
                    return;

                }
            }
        }
    }

    /**
     * Inserimento manuale del libro.
     * I dati del libro vengono immessi manualmente dall'utente
     * @throws CourseException Viene sollevata nel caso in cui il corso non sia stato trovato
     * @throws EscCliException Viene sollevata nel caso in cui l'utente digita esc per tornare alla home
     */
    private void insertBookManual() throws CourseException, EscCliException {

        Printer.clear();
        Printer.println("--- INSERIMENTO MANUALE LIBRO ---");

        while(true) {
            try {

                // Viene richiesto l'ISBN del libro all'utente
                String isbn = requestBookCode(ISBN_REQUEST_TEXT);
                // Viene chiesto il titolo del libro all'utente
                String name = requestString(TITLE_REQUEST_TEXT);

                // Viene creato il bean contenente i dati del libro
                BookBean bookBean = new BookBean(isbn, name);
                // Vengono inviati i bean contenenti i dati del libro e del corso al controller grafico
                controller.insertBookInCourse(courseBean, bookBean);

                // Se non vengono sollevate eccezioni l'operazione è stata completata correttamente e viene stampato il messaggio di conferma
                Printer.println(SUCCESS_MESSAGE_TEXT);
                // Si attende un qualsiasi pulsante premuto per tornare alla home
                waitForExit();

                return;

            } catch (FieldNotValidException e) {
                showErrorMessage(e);
            }
        }

    }

    /**
     * Questo gestore viene eseguito quando il libro inserito dall'utente non viene trovato nella libreria
     * o se le informazioni che sono state trovate sono errate.
     * Il gestore chiede all'utente come procedere
     * @return <i>true</i> se l'utente chiede di riprovare in maniera automatica.
     *         <i>false</i> il dipendente decide di inserire i dati manualmente.
     * @throws CourseException Viene sollevata nel caso in cui il corso non sia stato trovato
     * @throws EscCliException Viene sollevata nel caso in cui l'utente digita esc per tornare alla home
     */
    private boolean wrongBookHandler() throws CourseException, EscCliException {

        Printer.clear();
        Printer.println("Seleziona come procedere");
        Printer.println("""
           [1] Riprova
           [2] Inserisci manualmente""");

        while (true) {
            try {
                // Richiede all'utente il codice dell'azione che vuole eseguire
                int selection = requestInt("Selezione: ");
                switch (selection) {
                    case 1 -> {                     // L'utente vuole riprovare in modalità automatica
                        return true;
                    }
                    case 2 -> {                     // L'utente vuole inserire i dati manualmente
                        insertBookManual();
                        return false;
                    }
                    default -> throw new SelectionNotValidException();
                }

            } catch (SelectionNotValidException exception) {
                showErrorMessage(exception);
            }
        }
    }

}
