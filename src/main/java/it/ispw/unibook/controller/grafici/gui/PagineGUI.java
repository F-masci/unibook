package it.ispw.unibook.controller.grafici.gui;

public enum PagineGUI {
    LOGIN("/gui/Login.fxml"),
    HOME_PROFESSORE("/gui/professore/Home.fxml");

    private String path;

    PagineGUI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
