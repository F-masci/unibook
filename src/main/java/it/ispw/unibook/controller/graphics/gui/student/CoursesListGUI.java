package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoursesListGUI extends ManageSellableBookGUI implements Initializable {

    @FXML
    private VBox coursesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CoursesListBean bean = new CoursesListBean();
        super.retrieveCourses(bean);
        List<CourseBean> courses = bean.getList();

        for (CourseBean c : courses) {
            String text = c.getCode() + " - " + c.toString();
            Label label = new Label(text);
            label.getStyleClass().add("course");
            coursesList.getChildren().add(label);
        }
    }
}
