package it.ispw.unibook.entity;

public class BookEntity {

    private final String ISBN;
    private final String title;

    public BookEntity(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }
    public String getTitle() {
        return title;
    }
}
