package it.ispw.unibook.controller.grafici.gui;

import it.ispw.unibook.controller.grafici.cli.Printer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * La classe gestisce l'interfaccia GUI del sistema.
 * Viene realizzata attraverso il pattern singleton in modo tale
 * che ogni componente del sistema può accedere per gessire le varie finestre.
 */
public class ControllerGUI {

    // Contiene l'unica istanza di ControllerGUI del sistema
    private static ControllerGUI instance = null;
    private Stage stage = null;
    private ControllerGUI() {}

    public static ControllerGUI getInstance() {
        if (instance == null) {
            instance = new ControllerGUI();
        }
        return instance;
    }

    public void impostaStage(Stage stage) {
        this.stage = stage;
    }

    public void impostaPagina(PagineGUI pagina) {
        try {
            Printer.println("Cambio pagina in -> " + pagina.getPath());
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pagina.getPath())));
            Scene scene = new Scene(root, 960, 540);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            Printer.error("Errore durante il caricamento della pagina: " + e.getMessage());
            System.exit(-1);
        }
    }

}
