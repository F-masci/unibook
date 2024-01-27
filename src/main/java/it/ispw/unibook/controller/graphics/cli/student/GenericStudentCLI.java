package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.exceptions.course.CourseException;

public class GenericStudentCLI {

    CourseController courseController = new CourseController();
    BookController bookController = new BookController();

    public void retrieveCourses(CoursesListBean bean) {
        courseController.retrieveCourses(bean);
    }
    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        bookController.retrieveBooksByCourse(bean);
    }

}
