package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.UnsellableBookInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;

public class InsertSellableBookController {
    public void insertSellableBook(SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        try {
            AccountEntity seller = SessionManager.getAccountBySessionID(bean.getSessionId());
            UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourse());
            SellableBookEntity sellableBook = new SellableBookEntity(bean.getISBN(), bean.getPrice(), seller);
            course.addSellableBook(sellableBook);
        } catch (UnsellableBookInCourseException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        } catch (CourseNotFoundException e) {
            throw new CourseException(e.getMessage(), e);
        }
    }

}
