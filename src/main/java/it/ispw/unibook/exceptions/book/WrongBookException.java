package it.ispw.unibook.exceptions.book;

public class WrongBookException extends BookException {

    private static final String DEFAULT_MSG = "Libro errato";

    public WrongBookException() {
        this(DEFAULT_MSG);
    }
    public WrongBookException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public WrongBookException(String msg) {
        super(msg);
    }
    public WrongBookException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
