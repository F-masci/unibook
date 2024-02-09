package it.ispw.unibook.exceptions;

public class FieldNotValidException extends RuntimeException {

    private static final String DEFAULT_MSG = "Campo non valido";

    public FieldNotValidException() {
        this(DEFAULT_MSG);
    }
    public FieldNotValidException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public FieldNotValidException(String msg) {
        super(msg);
    }
    public FieldNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
