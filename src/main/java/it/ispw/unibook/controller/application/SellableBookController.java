package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.EmailNotValidException;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class SellableBookController {

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        try {
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksBySeller(account);
            insertSellableBooksListIntoBean(sellableBooks, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    public SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
        List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksByIsbn(bean.getISBN());
        SellableBooksListBean resBean = new SellableBooksListBean();
        insertSellableBooksListIntoBean(sellableBooks, resBean);
        return resBean;
    }

    public SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) {
        CourseDao dao = CourseDaoFactory.getInstance().getDao();
        CourseEntity course = dao.retrieveCourseByCode(bean.getCode());
        List<SellableBookEntity> sellableBooks = course.getSellableBooks();
        SellableBooksListBean resBean = new SellableBooksListBean();
        insertSellableBooksListIntoBean(sellableBooks, resBean);
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

    private void insertSellableBooksListIntoBean(List<SellableBookEntity> sellableBooks, SellableBooksListBean bean) {
        List<SellableBookBean> list = new ArrayList<>();
        for(SellableBookEntity b: sellableBooks) {
            try {
                SellableBookBean sellableBook = new SellableBookBean(
                        b.getCode(),
                        b.getISBN(),
                        b.getTitle(),
                        b.getPrice()
                );
                list.add(sellableBook);
            } catch (BookException ignored) {}
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

}
