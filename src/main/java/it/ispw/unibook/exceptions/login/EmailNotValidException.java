package it.ispw.unibook.exceptions.login;

public class EmailNotValidException extends LoginException{

    public EmailNotValidException() {
        super("Email non valida");
    }

    public EmailNotValidException(String msg) {
        super(msg);
    }

}
