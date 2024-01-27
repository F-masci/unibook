package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.CourseEntityFacotry;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class RemoveSellableBookController {

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        try {
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksBySeller(account);
            insertCoursesListIntoBean(sellableBooks, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    public void removeSellableBook(SellableBookBean bean) throws SellableBookException, SessionException {
        try {
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(bean.getCode());
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            CourseEntity course = CourseEntityFacotry.getInstance().createCourseEntity(sellableBook, account);
            course.removeSellableBook(sellableBook);
        } catch (SellableBookNotFoundException | CourseNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    private void insertCoursesListIntoBean(List<SellableBookEntity> sellableBooks, SellableBooksListBean bean) {
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

}
