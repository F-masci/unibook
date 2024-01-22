package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;

import java.util.ArrayList;
import java.util.List;

public class BookController {

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
