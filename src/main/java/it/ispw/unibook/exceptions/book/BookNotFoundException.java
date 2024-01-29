package it.ispw.unibook.exceptions.book;

public class BookNotFoundException extends BookException{

    private final static String DEFAULT_MSG = "Libro non trovato";

    public BookNotFoundException() {
        this(DEFAULT_MSG);
    }
    public BookNotFoundException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BookNotFoundException(String msg) {
        super(msg);
    }
    public BookNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
