package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller applicativo per l'implementazione delle operazioni comuni
 * sulle entità Libro In Vendita richieste dai vari controller
 */
public class SellableBookController {

    /**
     * Carica nel bean la lista dei libri in vendita dell'utente loggato
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei libri in vendita collegati
     * @throws SessionException Viene sollevata in caso in cui il codice della sessione non sia valido
     */
    public void retrieveSellableBooksBySession(@NotNull SellableBooksListBean bean) throws SessionException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());

            // Viene usato il dao per ottenere dallo stato di persistenza tutti i libri in vendita associati
            // all'account che ha inviato il messaggio
            List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksBySeller(account);
            // Si carica la lista dei corsi all'interno del bean
            insertForSaleSellableBooksListIntoBean(sellableBooks, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }
    public void retrieveSellableBooksBySessionActiveNegotiation(@NotNull SellableBooksListBean bean) throws SessionException {
        try {
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksByNegotiation(account);
            insertForSaleSellableBooksListIntoBean(sellableBooks, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
        List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksByIsbn(bean.getISBN());
        SellableBooksListBean resBean = new SellableBooksListBean();
        insertForSaleSellableBooksListIntoBean(sellableBooks, resBean);
        return resBean;
    }

    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) throws CourseNotFoundException {
        UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
        CourseEntity course = dao.retrieveCourseByCode(bean.getCode());
        List<SellableBookEntity> sellableBooks = course.getSellableBooks();
        SellableBooksListBean resBean = new SellableBooksListBean();
        insertForSaleSellableBooksListIntoBean(sellableBooks, resBean);
        return resBean;
    }

    public AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookNotFoundException {
        SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
        SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(bean.getCode());
        List<AccountEntity> accounts = sellableBook.getBuyers();
        AccountsListBean resBean = new AccountsListBean();
        insertAccountsListIntoBean(accounts, resBean);
        return resBean;
    }

    private void insertForSaleSellableBooksListIntoBean(List<SellableBookEntity> sellableBooks, SellableBooksListBean bean) {
        insertSellableBooksListIntoBean(sellableBooks, bean, SellableBookFilter.ONLY_FOR_SALE);
    }

    private void insertSellableBooksListIntoBean(List<SellableBookEntity> sellableBooks, SellableBooksListBean bean, SellableBookFilter filter) {
        List<SellableBookBean> list = new ArrayList<>();
        for(SellableBookEntity b: sellableBooks) {
            switch (filter) {
                case ONLY_FOR_SALE:
                    if(b.isSold()) continue;
                    break;
                case ONLY_SOLD:
                    if(b.isForSale()) continue;
                    break;
            }
            try {
                SellableBookBean sellableBook = new SellableBookBean(
                        b.getCode(),
                        b.getIsbn(),
                        b.getTitle(),
                        b.getPrice()
                );
                list.add(sellableBook);
            } catch (BookException | SellableBookException ignored) {
                // Ignored
            }
        }
        bean.setList(list);
    }

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
