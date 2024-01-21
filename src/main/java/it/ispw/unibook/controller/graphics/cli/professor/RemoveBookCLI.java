package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;

public class RemoveBookCLI extends ManageBookCli {

    private final RemoveCourseBookController controller = new RemoveCourseBookController();

    public void removeBook(ManageBookBean bean) throws BookException {
        controller.removeBook(bean);
    }

}
