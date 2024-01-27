package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.exceptions.course.CourseException;

public class GenericStudentCLI {

    CourseController _courseController = new CourseController();
    BookController _bookController = new BookController();

    public void retrieveCourses(CoursesListBean bean) {
        _courseController.retrieveCourses(bean);
    }
    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        _bookController.retrieveBooksByCourse(bean);
    }

}
