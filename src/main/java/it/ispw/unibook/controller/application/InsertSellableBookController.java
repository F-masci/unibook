package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.UnsellableBookInCourseException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.utils.SessionManager;

public class InsertSellableBookController {

    public void insertSellableBook(SellableBookBean bean) throws SellableBookException {
        try {
            AccountEntity seller = SessionManager.getAccountBySessionID(bean.getSessionId());
            CourseDao dao = CourseDaoFactory.getInstance().getDao();
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourse());
            SellableBookEntity sellableBook = new SellableBookEntity(bean.getISBN(), bean.getPrice(), seller);
            course.addSellableBook(sellableBook);
        } catch (UnsellableBookInCourseException e) {
            throw (SellableBookException) new UnsellableBookInCourseException(e.getMessage()).initCause(e);
        }
    }

}
