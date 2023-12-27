package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;

public class RemoveBookCLI extends GenericControllerCLI {

    private final RemoveCourseBookController controller = new RemoveCourseBookController();

    public void removeBook(int course, String ISBN) throws ISBNNotValidException {
        ManageBookBean bean = new ManageBookBean(course, ISBN);
        controller.removeBook(bean);
    }

}
