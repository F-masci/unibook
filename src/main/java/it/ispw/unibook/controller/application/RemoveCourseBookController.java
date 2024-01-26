package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.factory.CourseDaoFactory;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class RemoveCourseBookController extends ManageCourseBookController {

    public void removeBookFromCourse(@NotNull ManageBookBean bean) throws BookException {
        try {
            BookEntity book = new BookEntity(bean.getISBN(), bean.getName());
            CourseDao dao = CourseDaoFactory.getInstance().getDao();
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourse());
            course.removeBook(book);
        } catch(BookNotInCourseException e) {
            throw (BookException) e.initCause(e);
        }
    }

}
