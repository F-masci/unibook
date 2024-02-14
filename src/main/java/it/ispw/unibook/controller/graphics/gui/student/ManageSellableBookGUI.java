package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import it.ispw.unibook.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.jetbrains.annotations.NotNull;

public abstract class ManageSellableBookGUI extends GenericGUI {

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_STUDENT);
    }

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade manageSellableBookFacade = new ManageSellableBookFacade();

    /**
     * Carica tutti i corsi del sistema nella combo
     * @param combo ComboBox su cui caricare i corsi
     */
    protected void loadAllCourses(@NotNull ComboBox<String> combo) {
        // Viene istanziato il bean che conterrà la lista dei corsi
        CoursesListBean bean = new CoursesListBean();
        // Viene caricata la lista dei corsi nel bean
        this.retrieveCourses(bean);
        // Viene caricata la lista dei corsi nel bean nella combo
        // La gestione della ComboBox è affidata alla classe padre
        super.loadCoursesComboBox(combo, bean);
    }

    /**
     * Carica i libri di un corso nella combo
     * @param combo ComboBox su cui caricare i libri
     * @param course Codice del corso
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    protected void loadCourseBooks(@NotNull ComboBox<String> combo, int course) throws CourseException {
        // Viene istanziato il bean che conterrà la lista dei libri del corso
        BooksListBean bean = new BooksListBean(course);
        // Viene caricata la lista dei libri nel bean
        this.retrieveBooksByCourse(bean);
        // Viene caricata la lista dei libri nel bean nella combo
        // La gestione della ComboBox è affidata alla classe padre
        super.loadCourseBooksComboBox(combo, bean);
    }

    /**
     * Carica i libri in vendita di un corso nella combo
     * @param combo ComboBox su cui caricare i libri in vendita
     * @param course Codice del corso
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    protected void loadCourseSellableBooks(@NotNull ComboBox<String> combo, int course) throws CourseException {
        // Viene istanziato il bean che contiene il codice del corso
        CourseBean courseBean = new CourseBean(course);
        // Viene ritornata la lista dei libri in vendita per il corso
        SellableBooksListBean sellableBooksBean =  this.retrieveSellableBooksByCourse(courseBean);
        // Viene caricata la lista dei libri in vendita nel bean nella combo
        // La gestione della ComboBox è affidata alla classe padre
        super.loadSellableBooksComboBox(combo, sellableBooksBean);
    }

    /**
     * Carica i libri in vendita nella combo
     * @param combo ComboBox su cui caricare i libri in vendita
     * @param isbn ISBN del libro di cui si vogliono cercare le copie in vendita
     * @throws FieldNotValidException Viene sollevata se l'ISBN non ha un formato valido
     */
    protected void loadIsbnSellableBooks(@NotNull ComboBox<String> combo, String isbn) throws FieldNotValidException {
        // Viene istanziato il bean che contiene l'isbn del libro
        BookBean bookBean = new BookBean(isbn);
        // Viene ritornata la lista dei libri in vendita con l'isbn fornito
        SellableBooksListBean sellableBooksBean = this.retrieveSellableBooksByIsbn(bookBean);
        // Viene caricata la lista dei libri in vendita nel bean nella combo
        // La gestione della ComboBox è affidata alla classe padre
        super.loadSellableBooksComboBox(combo, sellableBooksBean);
    }

    /**
     * Carica i libri in vendita dell'utente loggato nella combo
     * @param combo ComboBox su cui caricare i libri in vendita
     */
    protected void loadSessionSellableBooks(@NotNull ComboBox<String> combo) {
        try {
            // Viene istanziato il bean che conterrà la lista dei libri in vendita dell'utente loggato
            SellableBooksListBean bean = new SellableBooksListBean();
            // Viene caricata la lista dei libri in vendita nel bean
            this.retrieveSellableBooksBySession(bean);
            // Viene caricata la lista dei libri in vendita nel bean nella combo
            // La gestione della ComboBox è affidata alla classe padre
            super.loadSellableBooksComboBox(combo, bean);
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Carica gli acquirenti di un libro in vendita
     * @param combo ComboBox su cui caricare gli acquirenti
     * @param sellableBook Codice del libro in vendita
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato
     */
    protected void loadSellableBookBuyers(@NotNull ComboBox<String> combo, int sellableBook) throws SellableBookException {
        // Viene istanziato il bean che contiene il codice del libro in vendita
        SellableBookBean sellableBookBean = new SellableBookBean(sellableBook);
        // Viene ritornata la lista degli acquirenti del libro in vendita
        AccountsListBean accountsBean = this.retrieveActiveNegotiationBySellableBook(sellableBookBean);
        // Viene caricata la lista degli acquirenti nel bean nella combo
        // La gestione della ComboBox è affidata alla classe padre
        super.loadAccountsComboBox(combo, accountsBean);
    }


    /**
     * Ritorna la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi
     */
    protected void retrieveCourses(@NotNull CoursesListBean bean) {
        manageSellableBookFacade.retrieveCourses(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    protected void retrieveBooksByCourse(@NotNull BooksListBean bean) throws CourseException {
        manageSellableBookFacade.retrieveBooksByCourse(bean);
    }

    /**
     * Carica nel bean la lista dei libri in vendita dell'utente loggato
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita collegati
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    protected void retrieveSellableBooksBySession(@NotNull SellableBooksListBean bean) throws SessionException {
        manageSellableBookFacade.retrieveSellableBooksBySession(bean);
    }

    /**
     * Ritorna la lista dei libri in vendita cercandoli tramite ISBN
     * @param bean Deve contenere l'ISBN
     * @return Bean contenente la lista dei libri in vendita con l'ISBN fornito
     */
    protected SellableBooksListBean retrieveSellableBooksByIsbn(@NotNull BookBean bean) {
        return manageSellableBookFacade.retrieveSellableBooksByIsbn(bean);
    }

    /**
     * Ritorna la lista dei libri in vendita collegati al corso
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente la lista dei libri in vendita collegati al corso fornito
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    protected SellableBooksListBean retrieveSellableBooksByCourse(@NotNull CourseBean bean) throws CourseException {
        return manageSellableBookFacade.retrieveSellableBooksByCourse(bean);
    }

    /**
     * Carica nel bean la lista dei libri che l'utente loggato è interessato ad acquistare
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksByActiveNegotiationOfSession(@NotNull SellableBooksListBean bean) throws SessionException {
        manageSellableBookFacade.retrieveSellableBooksByActiveNegotiationOfSession(bean);
    }

    /**
     * Ritorna la lista degli acquirenti collegati al libro in vendita
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente i libri in vendita collegati al corso
     * @throws SellableBookException Viene sollevata se il libro in vendita non viene trovato
     */
    protected AccountsListBean retrieveActiveNegotiationBySellableBook(@NotNull SellableBookBean bean) throws SellableBookException {
        return manageSellableBookFacade.retrieveActiveNegotiationBySellableBook(bean);
    }

}
