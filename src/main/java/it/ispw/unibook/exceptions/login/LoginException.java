package it.ispw.unibook.exceptions.login;

public class LoginException extends Exception {

    public LoginException() {
        this("Credenziali inserite non valide");
    }
    public LoginException(Throwable e) {
        this("Credenziali inserite non valide", e);
    }

    public LoginException(String msg) {
        super(msg);
    }
    public LoginException(String msg, Throwable e) {
        super(msg, e);
    }

}
