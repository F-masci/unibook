package it.ispw.unibook.exceptions;

public class ISBNNotValidException extends FieldNotValidException {

    private static final String DEFAULT_MSG = "ISBN non valido";

    public ISBNNotValidException() {
        this(DEFAULT_MSG);
    }
    public ISBNNotValidException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public ISBNNotValidException(String msg) {
        super(msg);
    }
    public ISBNNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
