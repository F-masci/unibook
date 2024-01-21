package it.ispw.unibook.factory;

import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.CourseDaoUniJDBC;

public class CourseDaoFactory {

    private static CourseDaoFactory instance = null;

    private CourseDaoFactory() {}

    public static CourseDaoFactory getInstance() {
        if(instance == null) instance = new CourseDaoFactory();
        return instance;
    }

    public CourseDao getDao() {
        return new CourseDaoUniJDBC();
    }

}
