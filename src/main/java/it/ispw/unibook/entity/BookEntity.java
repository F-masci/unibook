package it.ispw.unibook.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return Objects.equals( ((BookEntity) o).getISBN(), this.getISBN() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getISBN());
    }
}
