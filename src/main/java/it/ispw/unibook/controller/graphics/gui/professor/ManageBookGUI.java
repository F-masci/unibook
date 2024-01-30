package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

import java.util.List;

public abstract class ManageBookGUI extends GenericGUI {

    ManageCourseBookFacade courseBookController = new ManageCourseBookFacade();

    private final ObservableMap<String, Integer> courses = FXCollections.observableHashMap();
    protected void loadCoursesComboBox(ComboBox<String> combo) {
        try {
            CoursesListBean bean = new CoursesListBean();
            retrieveCoursesBySession(bean);

            List<CourseBean> localCourses = bean.getList();
            for (CourseBean c : localCourses) {
                this.courses.put(c.toString(), c.getCode());
            }

            combo.setItems(FXCollections.observableArrayList(this.courses.keySet()));
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    protected int getCourseSelectedFromComboBox(ComboBox<String> combo) {
        String value = combo.getValue();
        if (value != null) {
            return courses.get(value);
        }
        return 0;
    }

    protected void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        courseBookController.retrieveCoursesBySession(bean);
    }
    protected void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        courseBookController.retrieveBooksByCourse(bean);
    }

}
