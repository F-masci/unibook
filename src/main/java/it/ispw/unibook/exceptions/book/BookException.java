package it.ispw.unibook.exceptions.book;

public class BookException extends Exception {

    public BookException() {
        this("Errore");
    }
    public BookException(Throwable cause) {
        this("Errore", cause);
    }

    public BookException(String msg) {
        super(msg);
    }
    public BookException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
