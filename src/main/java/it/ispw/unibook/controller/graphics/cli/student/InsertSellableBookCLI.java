package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.InsertSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;

public class InsertSellableBookCLI extends ManageSellableBookCLI {

    InsertSellableBookController controller = new InsertSellableBookController();

    public void insertSellableBook(SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        controller.insertSellableBook(bean);
    }

}
