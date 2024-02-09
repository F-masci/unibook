package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import it.ispw.unibook.utils.Printer;
import javafx.scene.control.ComboBox;

public abstract class ManageBookGUI extends GenericProfessorGUI {

    ManageCourseBookFacade manageCourseBookFacade = new ManageCourseBookFacade();

    protected void loadSessionCourses(ComboBox<String> combo) {
        try {
            CoursesListBean bean = new CoursesListBean();
            retrieveCoursesBySession(bean);
            loadCoursesComboBox(combo, bean);
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    protected void loadCourseBooks(ComboBox<String> combo, int course) throws CourseException {
        BooksListBean bean = new BooksListBean(course);
        retrieveBooksByCourse(bean);
        loadCourseBooksComboBox(combo, bean);
    }

    protected void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        manageCourseBookFacade.retrieveCoursesBySession(bean);
    }
    protected void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        manageCourseBookFacade.retrieveBooksByCourse(bean);
    }

}
