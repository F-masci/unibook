package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.application.InsertCourseBookController;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;

public class InsertBookCLI extends ManageBookCli {

    private final InsertCourseBookController controller = new InsertCourseBookController();

     public void getBookInformation(BookBean bean) throws BookException {
         controller.getBookInformation(bean);
     }

     public void insertBookInCourse(ManageBookBean bean) throws CourseException {
         controller.insertBookInCourse(bean);
     }

}
