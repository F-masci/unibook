package it.ispw.unibook.exceptions.login;

public class LoginException extends Exception {

    public LoginException() {
        super("Dati inseriti non validi");
    }

    public LoginException(String msg) {
        super(msg);
    }

}
