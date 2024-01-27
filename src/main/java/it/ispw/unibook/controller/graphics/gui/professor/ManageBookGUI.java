package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.ManageCourseBookController;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ManageBookGUI extends GenericGUI {

    ManageCourseBookController controller = new ManageCourseBookController();

    protected ManageBookGUI() {}

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }

    private final ObservableMap<String, Integer> courses = FXCollections.observableHashMap();
    protected void loadCoursesComboBox(ComboBox<String> combo) throws SessionException {

        CoursesListBean bean = new CoursesListBean();
        retrieveCoursesBySession(bean);

        List<CourseBean> courses = bean.getList();
        for(CourseBean c: courses) {
            this.courses.put(c.toString(), c.getCode());
        }

        combo.setItems(FXCollections.observableArrayList(this.courses.keySet()));
    }

    protected int getCourseSelectedFromComboBox(ComboBox<String> combo) {
        String value = combo.getValue();
        if (value != null) {
            return courses.get(value);
        }
        return 0;
    }

    // FIXME exceptions
    protected void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        controller.retrieveCoursesBySession(bean);
    }
    // FIXME exceptions
    protected void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        controller.retrieveBooksByCourse(bean);
    }

}
