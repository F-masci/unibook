package it.ispw.unibook.exceptions.book;

public class BookException extends Exception {

    private static final String DEFAULT_MSG = "Errore";

    public BookException() {
        this(DEFAULT_MSG);
    }
    public BookException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BookException(String msg) {
        super(msg);
    }
    public BookException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
