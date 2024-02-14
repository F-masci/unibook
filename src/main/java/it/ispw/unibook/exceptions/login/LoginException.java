package it.ispw.unibook.exceptions.login;

public class LoginException extends Exception {

    private static final String DEFAULT_MSG = "Credenziali inserite non valide";

    public LoginException() {
        this(DEFAULT_MSG);
    }
    public LoginException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public LoginException(String msg) {
        super(msg);
    }
    public LoginException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
