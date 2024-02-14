package it.ispw.unibook.exceptions.login;

public class IncorrectPasswordException extends LoginException {

    private static final String DEFAULT_MSG = "Password errata";

    public IncorrectPasswordException() {
        this(DEFAULT_MSG);
    }
    public IncorrectPasswordException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public IncorrectPasswordException(String msg) {
        super(msg);
    }
    public IncorrectPasswordException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
