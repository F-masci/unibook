package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.BookDaoAppJDBC;
import it.ispw.unibook.dao.LibraryDao;
import it.ispw.unibook.dao.LibraryDaoOL;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.exceptions.book.BookNotFoundException;

public class InsertCourseBookController {

    public void getBookInformation(BookBean bean) throws BookNotFoundException {

        // TODO: usare factory
        LibraryDao dao = new LibraryDaoOL();
        BookEntity book =  dao.getBookByISBN(bean.getISBN());

        bean.setName(book.getTitle());

    }

    public void insertBook(ManageBookBean bean) {

        // TODO: usare factory
        BookDao dao = new BookDaoAppJDBC();
        BookEntity book = new BookEntity(bean.getISBN(), bean.getName());
        dao.insertBook(bean.getCourse(), book);

    }

}
