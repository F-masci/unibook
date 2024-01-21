package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class GenericControllerGUI extends GenericGUI {

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }

    private ObservableMap<String, Integer> items;
    protected void loadCoursesComboBox(ComboBox<String> combo) {

        items = FXCollections.observableHashMap();

        CoursesListBean bean = new CoursesListBean();
        CourseController controller = new CourseController();
        controller.retriveCourseBySession(bean);

        List<CourseBean> courses = bean.getList();
        for(CourseBean c: courses) {
            items.put(c.toString(), c.getCode());
        }

        combo.setItems(FXCollections.observableArrayList(items.keySet()));
    }

    protected int getCourseSelectedFromComboBox(ComboBox<String> combo) {
        String value = combo.getValue();
        if (value != null) {
            return items.get(value);
        }
        return 0;
    }

}
