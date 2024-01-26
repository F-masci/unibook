package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.application.InsertSellableBookController;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;

public class InsertSellableCLI extends GenericStudentCLI {

    InsertSellableBookController controller = new InsertSellableBookController();

    public void insertSellableBook(SellableBookBean bean) throws SellableBookException {
        controller.insertSellableBook(bean);
    }

}
