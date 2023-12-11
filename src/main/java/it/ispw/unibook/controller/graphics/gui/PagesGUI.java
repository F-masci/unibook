package it.ispw.unibook.controller.graphics.gui;

public enum PagesGUI {
    LOGIN("/gui/Login.fxml"),
    HOME_PROFESSOR("/gui/professor/Home.fxml"),
    HOME_STUDENT("/gui/student/Home.fxml");

    private final String path;

    PagesGUI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
