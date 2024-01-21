package it.ispw.unibook.exceptions.login;

public class SessionNotFoundException extends Exception{

    public SessionNotFoundException() {
        super("Sessione non valida");
    }

    public SessionNotFoundException(String msg) {
        super(msg);
    }

}
