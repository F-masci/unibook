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
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ManageCourseBookController {

    public void retrieveCoursesBySession(CoursesListBean bean) {

        CourseDao dao = CourseDaoFactory.getInstance().getDao();
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

    public void retrieveBooksByCourse(BooksListBean bean) {

        CourseEntity course = new CourseEntity(bean.getCourseCode());
        List<BookEntity> books = course.getBooks();
        List<BookBean> list = new ArrayList<>();
        for(BookEntity b: books) {
            try {
                BookBean book = new BookBean(
                        b.getISBN(),
                        b.getTitle()
                );
                list.add(book);
            } catch(BookException ignored) {}
        }
        bean.setList(list);

    }

}
