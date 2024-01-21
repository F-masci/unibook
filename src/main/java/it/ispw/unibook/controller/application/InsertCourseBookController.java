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

public class InsertCourseBookController {

    public void getBookInformation(BookBean bean) throws BookException {

        try {

            // TODO: usare factory

            LibraryDao dao = new LibraryDaoOL();
            BookEntity book = dao.searchBookByISBN(bean.getISBN());

            bean.setName(book.getTitle());

        } catch (BookNotFoundException e) {
            throw (BookException) new BookNotFoundException().initCause(e);
        }

    }

    public void insertBook(ManageBookBean bean) throws CourseException {

        try {

            // TODO: usare factory
            BookEntity book = new BookEntity(bean.getISBN(), bean.getName());

            CourseDao dao = new CourseDaoUniJDBC();
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourse());
            course.addBook(book);
            course.saveBooks();

        } catch(BookAlreadyInCourseException e) {
            throw (CourseException) new BookAlreadyInCourseException().initCause(e);
        }

    }

}
