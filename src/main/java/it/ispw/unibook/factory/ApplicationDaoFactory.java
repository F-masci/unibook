package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;
import it.ispw.unibook.exceptions.PersistenceNotValidException;
import it.ispw.unibook.utils.ApplicationProperties;
import it.ispw.unibook.utils.Printer;

import java.util.Properties;

public abstract class ApplicationDaoFactory {

    private static ApplicationDaoFactory instance = null;
    public static ApplicationDaoFactory getInstance() {
        try {
            if (instance == null) {
                Properties config = ApplicationProperties.getApplicationProperties();
                switch (config.getProperty("persistence")) {
                    case "jdbc" -> instance = ApplicatonDaoFactoryJDBC.getInstance();
                    case "file" -> instance = ApplicationDaoFactoryFile.getInstance();
                    default -> throw new PersistenceNotValidException();
                }
            }
        } catch(PersistenceNotValidException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return instance;
    }

    public abstract LoginDao getLoginDao();
    public abstract AccountDao getAccountDao();
    public abstract CourseDao getCourseDao();
    public abstract BookDao getBookDao();
    public abstract SellableBookDao getSellableBookDao();
}
