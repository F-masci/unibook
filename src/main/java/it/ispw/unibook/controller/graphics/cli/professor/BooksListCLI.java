package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.controller.application.BookController;

public class BooksListCLI {

    public void getBooks(BooksListBean bean) {
        BookController controller = new BookController();
        controller.getBooks(bean);
    }



}
