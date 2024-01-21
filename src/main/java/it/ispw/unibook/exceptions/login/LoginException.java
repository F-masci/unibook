package it.ispw.unibook.exceptions.login;

public class LoginException extends Exception {

    public LoginException() {
        this("Credenziali inserite non valide");
    }

    public LoginException(String msg) {
        super(msg);
    }

}
