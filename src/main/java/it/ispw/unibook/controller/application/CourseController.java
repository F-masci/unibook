package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class CourseController {

    public void retrieveCoursesBySession(CoursesListBean bean) {
        CourseDao dao = CourseDaoFactory.getInstance().getDao();
        AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
        List<CourseEntity> courses = dao.retrieveCoursesByProfessor(account.getCode());
        insertCoursesListIncBean(courses, bean);
    }

    public void retrieveCourses(CoursesListBean bean) {
        CourseDao dao = CourseDaoFactory.getInstance().getDao();
        List<CourseEntity> courses = dao.retrieveCourses();
        insertCoursesListIncBean(courses, bean);
    }

    private void insertCoursesListIncBean(List<CourseEntity> courses, CoursesListBean bean) {
        List<CourseBean> list = new ArrayList<>();
        for(CourseEntity c: courses) {
            CourseBean course = new CourseBean(
                    c.getCode(),
                    c.getName(),
                    c.getStartYear(),
                    c.getEndYear()
            );
            list.add(course);
        }
        bean.setList(list);
    }

}
