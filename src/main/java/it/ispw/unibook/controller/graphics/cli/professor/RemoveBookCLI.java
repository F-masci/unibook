package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.application.RemoveCourseBookController;
import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.book.BookException;

public class RemoveBookCLI extends ManageBookCLI {

    private final RemoveCourseBookController controller = new RemoveCourseBookController();

    public void removeBookFromCourse(CourseBean courseBean, BookBean bookBean) throws BookException {
        controller.removeBookFromCourse(courseBean, bookBean);
    }

}
