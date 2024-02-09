package it.ispw.unibook.exceptions.account;

public class AccountNotFoundException extends AccountException {

    private static final String DEFAULT_MSG = "Account non trovato";

    public AccountNotFoundException() {
        this(DEFAULT_MSG);
    }
    public AccountNotFoundException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }
    public AccountNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
