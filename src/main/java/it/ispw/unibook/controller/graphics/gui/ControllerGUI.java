package it.ispw.unibook.controller.graphics.gui;

import it.ispw.unibook.utils.Printer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * La classe gestisce l'interfaccia GUI del sistema.
 */
public class ControllerGUI {

    private static Stage stage = null;
    private ControllerGUI() {}

    public static void setStage(Stage stage) {
        ControllerGUI.stage = stage;
    }

    public static void setPage(PagesGUI page) {
        try {
            // Viene caricata la nuova view
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(ControllerGUI.class.getResource(page.getPath())));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            Printer.error("Errore durante il caricamento della pagina: " + e.getMessage());
            System.exit(-1);
        }
    }

}
