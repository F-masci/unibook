package it.ispw.unibook.controller.grafici.cli;

import it.ispw.unibook.controller.grafici.ControllerUI;
import it.ispw.unibook.controller.grafici.gui.ControllerGUI;
import javafx.stage.Stage;

public class ControllerCLI implements ControllerUI {

    // Contiene l'unica istanza di ControllerCLI del sistema
    private static ControllerCLI instance = null;
    private ControllerCLI() {}

    public static ControllerCLI getInstance() {
        if (instance == null) {
            instance = new ControllerCLI();
        }
        return instance;
    }

    public void init() {}

}
