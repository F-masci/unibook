package it.ispw.unibook.controller.grafici.gui;

import it.ispw.unibook.controller.grafici.cli.Printer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class FinestraGUI extends Application {

    private static Stage stage = null;

    public static void avviaGUI() { launch(null); }
    public static Stage ottieniStage() { return stage; }

    @Override
    public void start(Stage stage) {
        stage.setTitle("UniBook");
        FinestraGUI.stage = stage;
    }

}
