package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.BookDaoAppJDBC;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.factory.ApplicationDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class BookController {

    public void getBooks(BooksListBean bean) {

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
