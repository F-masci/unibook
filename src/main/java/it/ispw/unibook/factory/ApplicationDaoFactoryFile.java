package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;

public class ApplicationDaoFactoryFile extends ApplicationDaoFactory {

    private static ApplicationDaoFactoryFile instance = null;
    private ApplicationDaoFactoryFile() {}

    public static ApplicationDaoFactoryFile getInstance() {
        if(instance == null) instance = new ApplicationDaoFactoryFile();
        return instance;
    }

    @Override
    public BookDao getBookDao() {
        return new BookDaoAppFile();
    }

    @Override
    public SellableBookDao getSellableBookDao() {
        return null;
    }

    @Override
    public LoginDao getLoginDao() {
        return new LoginDaoAppFile();
    }

    @Override
    public AccountDao getAccountDao() {
        return null;
    }

}
