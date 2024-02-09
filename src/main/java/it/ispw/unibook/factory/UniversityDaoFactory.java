package it.ispw.unibook.factory;

import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.dao.UniversityDaoJDBC;

public class UniversityDaoFactory {

    private static UniversityDaoFactory instance = null;

    private UniversityDaoFactory() {}

    public static UniversityDaoFactory getInstance() {
        if(instance == null) instance = new UniversityDaoFactory();
        return instance;
    }

    public UniversityDao getDao() {
        return new UniversityDaoJDBC();
    }

}
