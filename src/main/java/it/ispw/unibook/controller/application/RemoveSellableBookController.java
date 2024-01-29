package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.CourseEntityFacotry;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.utils.SessionManager;

public class RemoveSellableBookController extends ManageSellableBookController {

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

}
