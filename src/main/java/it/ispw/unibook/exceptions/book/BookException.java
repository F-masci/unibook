package it.ispw.unibook.exceptions.book;

public class BookException extends Exception {

    public BookException(String msg) {
        super(msg);
    }
    public BookException(String msg, Exception e) {
        super(msg, e);
    }

    public BookException() {
        this("Errore");
    }
    public BookException(Exception e) {
        this("Errore", e);
    }

}
