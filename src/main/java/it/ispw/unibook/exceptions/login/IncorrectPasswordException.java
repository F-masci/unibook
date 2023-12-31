package it.ispw.unibook.exceptions.login;

public class IncorrectPasswordException extends LoginException {

    public IncorrectPasswordException() {
        super("Password errata");
    }

    public IncorrectPasswordException(String msg) {
        super(msg);
    }

}
