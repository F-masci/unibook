package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.dao.*;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.factory.LibraryDaoFactory;
import org.jetbrains.annotations.NotNull;

public class InsertCourseBookController extends ManageCourseBookController {

    public void getBookInformation(@NotNull BookBean bean) throws BookException {
        try {
            LibraryDao dao = LibraryDaoFactory.getInstance().getDao();
            BookEntity book = dao.searchBookByISBN(bean.getISBN());
            bean.setName(book.getTitle());
        } catch (BookNotFoundException e) {
            throw (BookException) new BookNotFoundException(e.getMessage()).initCause(e);
        }
    }

    public void insertBookInCourse(@NotNull ManageBookBean bean) throws CourseException {
        try {
            BookEntity book = new BookEntity(bean.getISBN(), bean.getName());
            CourseDao dao = CourseDaoFactory.getInstance().getDao();
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourse());
            course.addBook(book);
            course.saveBooks();
        } catch(BookAlreadyInCourseException e) {
            throw (CourseException) new BookAlreadyInCourseException(e.getMessage()).initCause(e);
        }
    }

}
