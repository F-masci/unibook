package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.CourseDaoUniJDBC;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.Session;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class InsertBookCourseController {

    public InsertBookCourseController() {}

    public CoursesListBean getCourses() {

        // TODO: usare pattern abstract factory
        CourseDao dao = new CourseDaoUniJDBC();
        AccountEntity account = SessionManager.getAccountBySessionID(Session.getSessionId());
        List<CourseEntity> courses = dao.getProfessorCourses(account);
        return new CoursesListBean(courses);

    }

}
