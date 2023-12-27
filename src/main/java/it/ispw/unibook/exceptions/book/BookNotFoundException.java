package it.ispw.unibook.exceptions.book;

public class BookNotFoundException extends BookException{

    public BookNotFoundException(String msg) {
        super(msg);
    }

    public BookNotFoundException() {
        this("Libro non trovato");
    }

}
