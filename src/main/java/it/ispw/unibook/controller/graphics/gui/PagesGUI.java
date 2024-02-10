package it.ispw.unibook.controller.graphics.gui;

public enum PagesGUI {
    LOGIN("/it/ispw/unibook/gui/Login.fxml"),
    HOME_PROFESSOR("/it/ispw/unibook/gui/professor/Home.fxml"),
    COURSE_BOOKS_LIST_PROFESSOR("/it/ispw/unibook/gui/professor/BooksList.fxml"),
    INSERT_COURSE_BOOK_PROFESSOR("/it/ispw/unibook/gui/professor/InsertBook.fxml"),
    REMOVE_COURSE_BOOK_PROFESSOR("/it/ispw/unibook/gui/professor/RemoveBook.fxml"),
    HOME_STUDENT("/it/ispw/unibook/gui/student/Home.fxml"),
    COURSES_LIST_STUDENT("/it/ispw/unibook/gui/student/CoursesList.fxml"),
    COURSE_BOOKS_LIST_STUDENT("/it/ispw/unibook/gui/student/BooksList.fxml"),
    ACTIVE_NEGOTIATION_MENU_STUDENT("/it/ispw/unibook/gui/student/ActiveNegotiationMenu.fxml"),
    OWN_SELLABLE_BOOKS_LIST_STUDENT("/it/ispw/unibook/gui/student/OwnSellableBooksList.fxml"),
    ACTIVE_NEGOTIATION_LIST_STUDENT("/it/ispw/unibook/gui/student/ActiveNegotiationList.fxml"),
    PURCHASE_BOOK_MENU_STUDENT("/it/ispw/unibook/gui/student/PurchaseBookMenu.fxml"),
    GLOBAL_SEARCH_STUDENT("/it/ispw/unibook/gui/student/GlobalSearch.fxml"),
    INSERT_SELLABLE_BOOK_STUDENT("/it/ispw/unibook/gui/student/InsertSellableBook.fxml"),
    SEARCH_BY_COURSE_STUDENT("/it/ispw/unibook/gui/student/SearchByCourse.fxml"),
    REMOVE_SELLABLE_BOOK_STUDENT("/it/ispw/unibook/gui/student/RemoveSellableBook.fxml"),
    MARK_SELLABLE_BOOK_SOLD_STUDENT("/it/ispw/unibook/gui/student/MarkSellableBookSold.fxml");

    private final String path;

    PagesGUI(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
