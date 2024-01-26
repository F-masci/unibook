package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.bean.SellableBooksListBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.factory.CourseEntityFacotry;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.factory.SellableBookFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class RemoveSellableBookController {

    public void retrieveSellableBooksBySession(SellableBooksListBean bean) {
        SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
        AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
        List<SellableBookEntity> sellableBooks = dao.retrieveSellableBooksBySeller(account);
        insertCoursesListIntoBean(sellableBooks, bean);
    }

    public void removeSellableBook(SellableBookBean bean) throws SellableBookException {
        try {
            SellableBookEntity sellableBook = SellableBookFactory.getInstance().createSellableBookEntity(bean.getCode());
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
            CourseEntity course = CourseEntityFacotry.getInstance().createCourseEntity(sellableBook, account);
            course.removeSellableBook(sellableBook);
        } catch (SellableBookNotFoundException | CourseNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
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
