package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.BookController;
import it.ispw.unibook.controller.application.CourseController;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class GenericControllerGUI extends GenericGUI {

    CourseController _courseController = new CourseController();
    BookController _bookController = new BookController();

    protected GenericControllerGUI() {}

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_PROFESSOR);
    }

    private final ObservableMap<String, Integer> _items = FXCollections.observableHashMap();
    protected void loadCoursesComboBox(ComboBox<String> combo) {

        CoursesListBean bean = new CoursesListBean();
        _courseController.retriveCourseBySession(bean);

        List<CourseBean> courses = bean.getList();
        for(CourseBean c: courses) {
            this._items.put(c.toString(), c.getCode());
        }

        combo.setItems(FXCollections.observableArrayList(this._items.keySet()));
    }

    protected int getCourseSelectedFromComboBox(ComboBox<String> combo) {
        String value = combo.getValue();
        if (value != null) {
            return _items.get(value);
        }
        return 0;
    }

    protected void retrieveBooksByCourse(BooksListBean bean) {
        _bookController.retrieveBooksByCourse(bean);
    }

}
