package it.ispw.unibook.exceptions.login;

public class SessionException extends Exception {

    private static final String DEFAULT_MSG = "Errore";

    public SessionException() {
        this(DEFAULT_MSG);
    }
    public SessionException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SessionException(String msg) {
        super(msg);
    }
    public SessionException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
