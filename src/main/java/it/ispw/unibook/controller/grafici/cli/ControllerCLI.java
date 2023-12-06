package it.ispw.unibook.controller.grafici.cli;

public class ControllerCLI {

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
