package it.ispw.unibook.exceptions.login;

public class EmailNotFoundException extends LoginException {

    public EmailNotFoundException() {
        this("Email non trovata");
    }

    public EmailNotFoundException(String msg) {
        super(msg);
    }

}
