package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller applicativo per l'implementazione delle operazioni comuni
 * sulle entità Libro In Vendita richieste dai vari controller
 */
public class SellableBookController {

    /**
     * Carica nel bean la lista dei libri in vendita dell'utente loggato
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita collegati
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksBySession(@NotNull SellableBooksListBean bean) throws SessionException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            // Viene usato il dao per ottenere dallo strato di persistenza tutti i libri in vendita associati
            // all'account che ha inviato il messaggio
            List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksBySeller(account);
            // Si carica la lista dei corsi all'interno del bean
            insertForSaleSellableBooksListIntoBean(sellableBooks, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    /**
     * Carica nel bean la lista dei libri che l'utente loggato è interessato ad acquistare
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksByActiveNegotiationOfSession(@NotNull SellableBooksListBean bean) throws SessionException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            // Viene usato il dao per ottenere dallo strato di persistenza tutti i libri in vendita che
            // l'utente loggato è interessato ad acquistare
            List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksByNegotiation(account);
            // Si carica la lista dei libri in vendita all'interno del bean
            insertForSaleSellableBooksListIntoBean(sellableBooks, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    /**
     * Ritorna la lista dei libri in vendita cercandoli tramite ISBN
     * @param bean Deve contenere l'ISBN
     * @return Bean contenente la lista dei libri in vendita con l'ISBN fornito
     */
    public SellableBooksListBean retrieveSellableBooksByIsbn(@NotNull BookBean bean) throws SessionNotFoundException {
        // Si carica il dao per la comunicazione con la persistenza
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        // Viene usato il dao per ottenere dallo strato di persistenza tutti i libri in vendita con l'ISBN fornito
        List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksByIsbn(bean.getISBN());
        // Account dell'utente loggato
        AccountEntity loggedUser = SessionManager.getAccountBySessionID(bean.getSessionId());
        // Vengono filtrati i libri e vengono restituiti quelli in vendita da venditori che non sono l'utente loggato
        sellableBooks.removeIf(sellableBook -> Objects.equals(sellableBook.getSeller(), loggedUser));
        // Viene istanziato il bean da restituire al chiamante
        SellableBooksListBean resBean = new SellableBooksListBean();
        // Si carica la lista dei libri in vendita all'interno del bean
        insertForSaleSellableBooksListIntoBean(sellableBooks, resBean);
        return resBean;
    }

    /**
     * Ritorna la lista dei libri in vendita collegati al corso
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente la lista dei libri in vendita collegati al corso fornito
     * @throws CourseException Viene sollevata se il corso non viene trovato
     */
    public SellableBooksListBean retrieveSellableBooksByCourse(@NotNull CourseBean bean) throws CourseException, SessionNotFoundException {
        // Si carica il dao per la comunicazione con la persistenza
        UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
        // Viene cercato sulla persistenza il corso corrispondete al codice fornito
        // Se il corso non viene trovato viene sollevata l'eccezione
        CourseEntity course = dao.retrieveCourseByCode(bean.getCode());
        // Viene ottenuta la lista dei libri in vendita collegati al corso
        List<SellableBookEntity> sellableBooks = course.getSellableBooks();
        // Account dell'utente loggato
        AccountEntity loggedUser = SessionManager.getAccountBySessionID(bean.getSessionId());
        // Vengono filtrati i libri e vengono restituiti quelli in vendita da venditori che non sono l'utente loggato
        sellableBooks.removeIf(sellableBook -> Objects.equals(sellableBook.getSeller(), loggedUser));
        // Viene istanziato il bean da restituire al chiamante
        SellableBooksListBean resBean = new SellableBooksListBean();
        // Si carica la lista dei libri in vendita all'interno del bean
        insertForSaleSellableBooksListIntoBean(sellableBooks, resBean);
        return resBean;
    }

    /**
     * Ritorna la lista degli acquirenti collegati al libro in vendita
     * @param bean Deve contenere il codice del corso
     * @return Bean contenente i libri in vendita collegati al corso
     * @throws SellableBookException Viene sollevata se il libro in vendita non viene trovato
     */
    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookException {
        // Si carica il dao per la comunicazione con la persistenza
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        // Viene cercato sulla persistenza il libro in vendita corrispondete al codice fornito
        // Se il libro in vendita non viene trovato viene sollevata l'eccezione
        SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(bean.getCode());
        // Viene ottenuta la lista degli acquirenti collegati al libro in vendita
        List<AccountEntity> accounts = sellableBook.getBuyers();
        // Viene istanziato il bean da restituire al chiamante
        AccountsListBean resBean = new AccountsListBean();
        // Si carica la lista degli acquirenti all'interno del bean
        insertAccountsListIntoBean(accounts, resBean);
        return resBean;
    }

    /**
     * Funzione ausiliare per formattare la lista da inserire nel bean
     * a partire dalla lista di entità.
     * Vengono filtrati solo i libri ancora in vendita
     * @param sellableBooks Lista di entità da cui creare la lista per il bean
     * @param bean Bean su cui caricare la lista
     */
    private void insertForSaleSellableBooksListIntoBean(List<SellableBookEntity> sellableBooks, SellableBooksListBean bean) {
        insertSellableBooksListIntoBean(sellableBooks, bean, SellableBookFilter.ONLY_FOR_SALE);
    }

    /**
     * Funzione ausiliare per formattare la lista da inserire nel bean
     * a partire dalla lista di entità
     * @param sellableBooks Lista di entità da cui creare la lista per il bean
     * @param bean Bean su cui caricare la lista
     * @param filter Filtro da utilizzare per scegliere i libri da inserire nel bean
     */
    private void insertSellableBooksListIntoBean(List<SellableBookEntity> sellableBooks, SellableBooksListBean bean, SellableBookFilter filter) {
        // Si prepara la lista da restituire al chiamante
        List<SellableBookBean> list = new ArrayList<>();
        for(SellableBookEntity b: sellableBooks) {
            // Viene controllato il filtro
            if((filter == SellableBookFilter.ONLY_FOR_SALE && !b.isForSale()) || (filter == SellableBookFilter.ONLY_SOLD && !b.isSold())) continue;
            // Viene istanziato il bean del libro a partire dall'entità
            SellableBookBean sellableBook = new SellableBookBean(
                    b.getCode(),
                    b.getIsbn(),
                    b.getTitle(),
                    b.getPrice(),
                    b.getSeller().getEmail()
            );
            // Si aggiunge il bean alla lista
            list.add(sellableBook);
        }
        // Viene impostata la lista nel bean
        bean.setList(list);
    }

    /**
     * Funzione ausiliare per formattare la lista da inserire nel bean
     * a partire dalla lista di entità
     * @param accounts Lista di entità da cui creare la lista per il bean
     * @param bean Bean su cui caricare la lista
     */
    private void insertAccountsListIntoBean(List<AccountEntity> accounts, AccountsListBean bean) {
        List<AccountBean> list = new ArrayList<>();
        for(AccountEntity a: accounts) {
            AccountBean account = new AccountBean(
                    a.getCode(),
                    a.getEmail(),
                    a.getName(),
                    a.getSurname()
            );
            list.add(account);
        }
        bean.setList(list);
    }

    private enum SellableBookFilter {
        ONLY_SOLD, ONLY_FOR_SALE, ALL;
    }

}
