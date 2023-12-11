package it.ispw.unibook.controller.graphics.gui.professor;

import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.ManageCourseBooksController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeGUI implements Initializable {

    @FXML
    private VBox coursesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManageCourseBooksController controller = new ManageCourseBooksController();
        CoursesListBean bean = new CoursesListBean();
        // controller.getCourses(bean);

        /*if(bean.first()) {
            do {
                String text = bean.getName() + " - " + String.valueOf(bean.getStartYear()) + "/" + String.valueOf(bean.getEndYear());
                Button button = new Button(text);
                button.getStyleClass().add("course");
                coursesList.getChildren().add(button);
            } while(bean.next());
        }*/
    }

}
