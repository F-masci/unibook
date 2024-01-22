package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.ManageCourseBookController;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class ManageBookGUI extends GenericGUI {

    ManageCourseBookController _controller = new ManageCourseBookController();

    protected ManageBookGUI() {}

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }

    private final ObservableMap<String, Integer> _courses = FXCollections.observableHashMap();
    protected void loadCoursesComboBox(ComboBox<String> combo) {

        CoursesListBean bean = new CoursesListBean();
        retrieveCoursesBySession(bean);

        List<CourseBean> courses = bean.getList();
        for(CourseBean c: courses) {
            this._courses.put(c.toString(), c.getCode());
        }

        combo.setItems(FXCollections.observableArrayList(this._courses.keySet()));
    }

    protected int getCourseSelectedFromComboBox(ComboBox<String> combo) {
        String value = combo.getValue();
        if (value != null) {
            return _courses.get(value);
        }
        return 0;
    }

    protected void retrieveCoursesBySession(CoursesListBean bean) {
        _controller.retrieveCoursesBySession(bean);
    }
    protected void retrieveBooksByCourse(BooksListBean bean) {
        _controller.retrieveBooksByCourse(bean);
    }

}
