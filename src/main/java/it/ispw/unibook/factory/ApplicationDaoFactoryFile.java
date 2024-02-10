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
        return BookDaoAppFile.getInstance();
    }

    @Override
    public SellableBookDao getSellableBookDao() {
        return SellableBookDaoAppFile.getInstance();
    }

    @Override
    public LoginDao getLoginDao() {
        return LoginDaoAppFile.getInstance();
    }

    @Override
    public AccountDao getAccountDao() {
        return new AccountDaoAppJDBC();
    }

    @Override
    public CourseDao getCourseDao() {
        return CourseDaoAppFile.getInstance();
    }

}
