package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.Bean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.CourseController;

public class CoursesListCLI {

    public void getCourses(CoursesListBean bean) {
        CourseController controller = new CourseController();
        controller.getCourses(bean);
    }

}
