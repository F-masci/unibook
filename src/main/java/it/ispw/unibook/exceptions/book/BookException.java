package it.ispw.unibook.exceptions.book;

public class BookException extends Exception {

    public BookException(String msg) {
        super(msg);
    }

    public BookException() {
        this("Errore");
    }

}
