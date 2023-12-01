package it.ispw.unibook.controller.grafici.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class LoginGUI extends Application {

    private final Logger logger = Logger.getLogger( this.getClass().getName() );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));

        Scene scene = new Scene(root, 960, 540);

        stage.setTitle("UniBook");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void accedi() {
        logger.info("Inizio login");
    }
}
