package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;

public class RemoveBookCLI extends ManageBookCli {

    private final RemoveCourseBookController controller = new RemoveCourseBookController();

    public void removeBookFromCourse(ManageBookBean bean) throws BookException {
        controller.removeBookFromCourse(bean);
    }

}
