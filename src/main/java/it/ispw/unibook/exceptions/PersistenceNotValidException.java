package it.ispw.unibook.exceptions;

public class PersistenceNotValidException extends Exception {

    private static final String DEFAULT_MSG = "Persistenza selezionata non valida";

    public PersistenceNotValidException() {
        this(DEFAULT_MSG);
    }
    public PersistenceNotValidException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public PersistenceNotValidException(String msg) {
        super(msg);
    }
    public PersistenceNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
