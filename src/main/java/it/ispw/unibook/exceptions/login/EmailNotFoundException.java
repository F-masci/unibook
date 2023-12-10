package it.ispw.unibook.exceptions.login;

public class EmailNotFoundException extends LoginException{

    public EmailNotFoundException() {
        super("Email non trovata");
    }

    public EmailNotFoundException(String msg) {
        super(msg);
    }

}
