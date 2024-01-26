package it.ispw.unibook.entity;

import java.util.Objects;

public class BookEntity {

    private final String isbn;
    private final String title;

    public BookEntity(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getISBN() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        return Objects.equals( ((BookEntity) o).getISBN(), this.getISBN() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getISBN());
    }
}
