package it.ispw.unibook.factory;

import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;

public class CourseEntityFacotry {

    private static CourseEntityFacotry instance = null;
    UniversityDao dao = UniversityDaoFactory.getInstance().getDao();

    private CourseEntityFacotry() {}

    public static CourseEntityFacotry getInstance() {
        if(instance == null) instance = new CourseEntityFacotry();
        return instance;
    }

    public CourseEntity createCourseEntity(int code) throws CourseNotFoundException {
        CourseEntity course = dao.retrieveCourseByCode(code);
        if(course == null) throw new CourseNotFoundException();
        return course;
    }

    public CourseEntity createCourseEntity(SellableBookEntity sellableBook, AccountEntity seller) throws CourseNotFoundException {
        CourseEntity course = dao.retrieveCourseBySellableBook(sellableBook, seller);
        if (course == null) throw new CourseNotFoundException();
        return course;
    }

}
