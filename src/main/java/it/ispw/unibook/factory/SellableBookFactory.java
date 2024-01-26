package it.ispw.unibook.factory;

import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;

public class SellableBookFactory {

    private static SellableBookFactory instance = null;
    SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();

    private SellableBookFactory() {}

    public static SellableBookFactory getInstance() {
        if(instance == null) instance = new SellableBookFactory();
        return instance;
    }

    public SellableBookEntity createSellableBookEntity(int code) throws SellableBookNotFoundException {
        SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(code);
        if (sellableBook == null) throw new SellableBookNotFoundException();
        return sellableBook;
    }

}
