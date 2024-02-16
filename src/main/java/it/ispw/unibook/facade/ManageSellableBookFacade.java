package it.ispw.unibook.facade;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.*;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import org.jetbrains.annotations.NotNull;

/**
 * Applicazione del pattern <i>façade</i> per fornire un accesso unico al sottosistema per il reperimento
 * dei dati dei libri in vendita.<br>
 * La classe espone i metodi per gestire sulla persistenza i libri in vendita necessari alle View
 */
public class ManageSellableBookFacade {

    // Controller per ottenere le informazioni dei corsi
    private final CourseController courseController = new CourseController();
    // Controller per ottenere le informazioni dei libri
    private final BookController bookController = new BookController();
    // Controller per ottenere le informazioni dei libri in vendita
    private final SellableBookController sellableBookController = new SellableBookController();
    // Controller per eseguire il caso d'uso <i>Inserisci libro in vendita</i>
    private final InsertSellableBookController insertSellableBookController = new InsertSellableBookController();
    // Controller per eseguire il caso d'uso <i>Rimuovi libro in vendita</i>
    private final RemoveSellableBookController removeSellableBookController = new RemoveSellableBookController();
    // Controller per eseguire il caso d'uso <i>Notifica di voler acquistare un libro</i>
    private final PurchaseBookController purchaseBookController = new PurchaseBookController();
    // Controller per eseguire il caso d'uso <i>Segna libro come venduto</i>
    private final MarkSellableBookSoldController markSellableBookSoldController = new MarkSellableBookSoldController();

    /**
     * Ritorna la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi
     */
    public void retrieveCourses(@NotNull CoursesListBean bean) {
        courseController.retrieveCourses(bean);
    }

    /**
     * Ritorna la lista dei libri associati a un corso
     * @param bean Deve contenere il codice del corso.
     *             Contiene la lista dei corsi
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public void retrieveBooksByCourse(@NotNull BooksListBean bean) throws CourseException {
        bookController.retrieveBooksByCourse(bean);
    }

    /**
     * Carica nel bean la lista dei libri in vendita dell'utente loggato
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita collegati
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksBySession(@NotNull SellableBooksListBean bean) throws SessionException {
        sellableBookController.retrieveSellableBooksBySession(bean);
    }

    /**
     * Carica nel bean la lista dei libri che l'utente loggato è interessato ad acquistare
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksByActiveNegotiationOfSession(@NotNull SellableBooksListBean bean) throws SessionException {
        sellableBookController.retrieveSellableBooksByActiveNegotiationOfSession(bean);
    }

    /**
     * Ritorna la lista dei libri in vendita cercandoli tramite ISBN
     * @param bean Deve contenere l'ISBN
     * @return Bean contenente la lista dei libri in vendita con l'ISBN fornito
     */
    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) throws SessionNotFoundException {
        return sellableBookController.retrieveSellableBooksByIsbn(bean);
    }

    /**
     * Ritorna la lista dei libri in vendita collegati al corso
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente la lista dei libri in vendita collegati al corso fornito
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) throws CourseException, SessionNotFoundException {
        return sellableBookController.retrieveSellableBooksByCourse(bean);
    }

    /**
     * Ritorna la lista degli acquirenti collegati al libro in vendita
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente i libri in vendita collegati al corso
     * @throws SellableBookException Viene sollevata se il libro in vendita non viene trovato
     */
    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookException {
        return sellableBookController.retrieveActiveNegotiationBySellableBook(bean);
    }

    /*-- INSERIMENTO --*/

    /**
     * Inserisce il libro in vendita completando il caso d'uso
     * @param bean Deve contenere i dati del libro da inserire e il codice della sessione dell'utente che lo sta inserendo
     * @throws SellableBookException Viene sollevata se il libro non può essere venduto in questo corso
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void insertSellableBook(SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        insertSellableBookController.insertSellableBook(bean);
    }

    /*-- RIMOZIONE --*/

    /**
     * Rimuove il libro in vendita completando il caso d'uso
     * @param bean Deve contenere il codice del libro in vendita
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void removeSellableBook(@NotNull SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        removeSellableBookController.removeSellableBook(bean);
    }

    /*-- GESTIONE --*/

    /**
     * L'utente loggato viene inserito tra gli acquirenti del libro completando il caso d'uso
     * @param bean Deve contenere il libro che l'utente loggato vuole comprare
     * @throws SellableBookException Viene sollevata se il libro non viene trovato
     * @throws NegotiationException Viene sollevata se l'acquirente è già tra la lista degli acquirenti
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void purchaseBook(@NotNull SellableBookBean bean) throws SellableBookException, NegotiationException, SessionException {
        purchaseBookController.purchaseBook(bean);
    }

    /**
     * Segna il libro come venduto completando il caso d'uso
     * @param sellableBookBean Deve contenere il codice del libro in vendita
     * @param buyerBean Deve contenere il codice dell'account dell'acquirente
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato o è stato già venduto
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void markSellableBookSold(@NotNull SellableBookBean sellableBookBean, @NotNull AccountBean buyerBean) throws SellableBookException, AccountNotFoundException, SessionException {
        markSellableBookSoldController.markSellableBookSold(sellableBookBean, buyerBean);
    }

}
