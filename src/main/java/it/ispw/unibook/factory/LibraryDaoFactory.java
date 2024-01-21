package it.ispw.unibook.factory;

import it.ispw.unibook.dao.LibraryDao;
import it.ispw.unibook.dao.LibraryDaoOL;

public class LibraryDaoFactory {

    private static LibraryDaoFactory instance = null;

    private LibraryDaoFactory() {}

    public static LibraryDaoFactory getInstance() {
        if(instance == null) instance = new LibraryDaoFactory();
        return instance;
    }

    public LibraryDao getDao() {
        return new LibraryDaoOL();
    }

}
