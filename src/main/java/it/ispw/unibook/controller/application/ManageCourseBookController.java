package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ManageCourseBookController {

    CourseController _courseController = new CourseController();
    BookController _bookController = new BookController();

    public void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        _courseController.retrieveCoursesBySession(bean);
    }

    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        _bookController.retrieveBooksByCourse(bean);
    }

}
