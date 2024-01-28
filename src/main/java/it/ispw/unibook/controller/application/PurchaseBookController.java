package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
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
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.exceptions.negotiation.BuyerAlreadyInNegotiationException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class PurchaseBookController extends ManageSellableBookController {

    public void purchaseBook(SellableBookBean bean) throws SellableBookException, NegotiationException, SessionException {
        try {
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(bean.getCode());
            AccountEntity buyer = SessionManager.getAccountBySessionID(bean.getSessionId());
            sellableBook.addBuyer(buyer);
        } catch (SellableBookNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch (BuyerAlreadyInNegotiationException e) {
            throw new NegotiationException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
