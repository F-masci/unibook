package it.ispw.unibook.exceptions.login;

public class EmailNotFoundException extends LoginException {

    private static final String DEFAULT_MSG = "Email non trovata";

    public EmailNotFoundException() {
        this(DEFAULT_MSG);
    }
    public EmailNotFoundException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public EmailNotFoundException(String msg) {
        super(msg);
    }
    public EmailNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
