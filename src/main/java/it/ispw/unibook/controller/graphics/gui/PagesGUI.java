package it.ispw.unibook.controller.graphics.gui;

public enum PagesGUI {
    LOGIN("/gui/Login.fxml"),
    HOME_PROFESSOR("/gui/professor/Home.fxml"),
    COURSE_BOOKS_LIST_PROFESSOR("/gui/professor/BooksList.fxml"),
    INSERT_COURSE_BOOK_PROFESSOR("/gui/professor/InsertBook.fxml"),
    HOME_STUDENT("/gui/student/Home.fxml");

    private final String path;

    PagesGUI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
