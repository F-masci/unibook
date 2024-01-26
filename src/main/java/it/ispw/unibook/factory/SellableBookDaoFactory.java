package it.ispw.unibook.factory;

import it.ispw.unibook.dao.LibraryDao;
import it.ispw.unibook.dao.LibraryDaoOL;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.dao.SellableBookDaoAppJDBC;

public class SellableBookDaoFactory {

    private static SellableBookDaoFactory instance = null;

    private SellableBookDaoFactory() {}

    public static SellableBookDaoFactory getInstance() {
        if(instance == null) instance = new SellableBookDaoFactory();
        return instance;
    }

    public SellableBookDao getDao() {
        return new SellableBookDaoAppJDBC();
    }

}
