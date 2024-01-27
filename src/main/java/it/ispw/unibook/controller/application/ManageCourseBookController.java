package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;

public class ManageCourseBookController {

    CourseController courseController = new CourseController();
    BookController bookController = new BookController();

    public void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        courseController.retrieveCoursesBySession(bean);
    }

    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        bookController.retrieveBooksByCourse(bean);
    }

}
