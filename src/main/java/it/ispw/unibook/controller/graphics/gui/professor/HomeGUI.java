package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.InsertBookCourseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeGUI implements Initializable {

    @FXML
    private VBox coursesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InsertBookCourseController controller = new InsertBookCourseController();
        CoursesListBean bean = controller.getCourses();

        if(bean.first()) {
            do {
                Label label = new Label(bean.getName());
                coursesList.getChildren().add(label);
            } while(bean.next());
        }
    }

}
