package it.ispw.unibook.controller.graphics.gui;

public enum PagesGUI {
    LOGIN("/gui/Login.fxml"),
    HOME_PROFESSOR("/gui/professor/Home.fxml"),
    COURSE_BOOKS_LIST_PROFESSOR("/gui/professor/BooksList.fxml"),
    INSERT_COURSE_BOOK_PROFESSOR("/gui/professor/InsertBook.fxml"),
    REMOVE_COURSE_BOOK_PROFESSOR("/gui/professor/RemoveBook.fxml"),
    HOME_STUDENT("/gui/student/Home.fxml"),
    COURSES_LIST_STUDENT("/gui/student/CoursesList.fxml"),
    COURSE_BOOKS_LIST_STUDENT("/gui/student/BooksList.fxml"),
    ACTIVE_NEGOTIATION_MENU_STUDENT("/gui/student/ActiveNegotiationMenu.fxml"),
    OWN_SELLABLE_BOOKS_LIST_STUDENT("/gui/student/OwnSellableBooksList.fxml"),
    ACTIVE_NEGOTIATION_LIST_STUDENT("/gui/student/ActiveNegotiationList.fxml"),
    PURCHASE_BOOK_MENU_STUDENT("/gui/student/PurchaseBookMenu.fxml"),
    GLOBAL_SEARCH_STUDENT("/gui/student/GlobalSearch.fxml"),
    INSERT_SELLABLE_BOOK_STUDENT("/gui/student/InsertSellableBook.fxml"),
    SEARCH_BY_COURSE_STUDENT("/gui/student/SearchByCourse.fxml"),
    REMOVE_SELLABLE_BOOK_STUDENT("/gui/student/RemoveSellableBook.fxml"),
    MARK_SELLABLE_BOOK_SOLD_STUDENT("/gui/student/MarkSellableBookSold.fxml");

    private final String path;

    PagesGUI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
