package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.AccountDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.factory.ApplicationDaoFactory;

public class MarkSellableBookSoldController {

    public void markSellableBookSold(SellableBookBean sellableBookBean, AccountBean buyerBean) throws SellableBookException {
        try {
            SellableBookDao sellableBookDao = ApplicationDaoFactory.getInstance().getSellableBookDao();
            SellableBookEntity sellableBook = sellableBookDao.retrieveSellableBookByCode(sellableBookBean.getCode());
            AccountDao accountDao = ApplicationDaoFactory.getInstance().getAccountDao();
            AccountEntity buyer = accountDao.retrieveAccountByCode(buyerBean.getCode());
            sellableBook.markAsSold(buyer);
        } catch(SellableBookAlreadySoldException | SellableBookNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
        }
    }

}
