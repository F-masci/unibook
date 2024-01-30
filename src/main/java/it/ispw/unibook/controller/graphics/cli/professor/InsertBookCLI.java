package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.application.InsertCourseBookController;
import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;

public class InsertBookCLI extends ManageBookCLI {

    private final InsertCourseBookController controller = new InsertCourseBookController();

     public void getBookInformation(BookBean bean) throws BookException {
         controller.getBookInformation(bean);
     }

     public void insertBookInCourse(CourseBean courseBean, BookBean bookBean) throws CourseException {
         controller.insertBookInCourse(courseBean, bookBean);
     }

}
