package it.ispw.unibook.controller.graphics.gui;

public enum PagesGUI {
    LOGIN("/gui/Login.fxml"),
    HOME_PROFESSOR("/gui/professor/Home.fxml");

    private String path;

    PagesGUI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
