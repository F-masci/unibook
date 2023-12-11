package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.ManageCourseBooksController;

import java.util.ArrayList;
import java.util.List;

public class BooksListCLI {

    public void getBooks(BooksListBean bean) {
        BookController controller = new BookController();
        controller.getBooks(bean);
    }



}
