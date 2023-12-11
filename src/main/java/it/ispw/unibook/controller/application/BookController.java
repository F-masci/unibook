package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.BookDaoAppJDBC;
import it.ispw.unibook.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookController {

    public void getBooks(BooksListBean bean) {

        // TODO: usare pattern abstract factory
        BookDao dao = new BookDaoAppJDBC();
        List<BookEntity> books = dao.getCourseBooks(bean.getCourseCode());
        List<BookBean> list = new ArrayList<>();
        for(BookEntity b: books) {
            BookBean book = new BookBean(
                    b.getISBN(),
                    b.getTitle()
            );
            list.add(book);
        }
        bean.setList(list);

    }

}
