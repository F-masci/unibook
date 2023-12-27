package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.Bean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.CourseDaoUniJDBC;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ViewCoursesController {

    public void retriveCoursesBySession(CoursesListBean bean) {

        // TODO: usare pattern abstract factory
        CourseDao dao = new CourseDaoUniJDBC();
        AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());
        List<CourseEntity> courses = dao.getProfessorCourses(account.getCode());
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
