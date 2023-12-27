package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.BookDaoAppJDBC;

public class RemoveCourseBookController {

    public void removeBook(ManageBookBean bean) {

        // TODO: usare factory
        BookDao dao = new BookDaoAppJDBC();
        dao.removeBookByISBN(bean.getCourse(), bean.getISBN());

    }

}
