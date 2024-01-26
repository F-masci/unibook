package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;

public class ApplicatonDaoFactoryJDBC extends ApplicationDaoFactory {

    private static ApplicatonDaoFactoryJDBC instance = null;
    private ApplicatonDaoFactoryJDBC() {};

    public static ApplicatonDaoFactoryJDBC getInstance() {
        if(instance == null) instance = new ApplicatonDaoFactoryJDBC();
        return instance;
    }

    @Override
    public BookDao getBookDao() {
        return new BookDaoAppJDBC();
    }
    @Override
    public SellableBookDao getSellableBookDao() {
        return new SellableBookDaoAppJDBC();
    }
    @Override
    public LoginDao getLoginDao() {
        return new LoginDaoAppJDBC();
    }
}
