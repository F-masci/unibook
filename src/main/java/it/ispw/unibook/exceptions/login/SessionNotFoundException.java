package it.ispw.unibook.exceptions.login;

public class SessionNotFoundException extends SessionException {

    private static final String DEFAULT_MSG = "Sessione non valida";

    public SessionNotFoundException() {
        this(DEFAULT_MSG);
    }
    public SessionNotFoundException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SessionNotFoundException(String msg) {
        super(msg);
    }
    public SessionNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
