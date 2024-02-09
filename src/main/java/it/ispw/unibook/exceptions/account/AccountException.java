package it.ispw.unibook.exceptions.account;

public class AccountException extends Exception {

    private static final String DEFAULT_MSG = "Errore";

    public AccountException() {
        this(DEFAULT_MSG);
    }
    public AccountException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public AccountException(String msg) {
        super(msg);
    }
    public AccountException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
