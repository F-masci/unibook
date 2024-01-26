package it.ispw.unibook.exceptions.login;

public class SessionNotFoundException extends SessionException {

    public SessionNotFoundException() {
        this("Sessione non valida");
    }
    public SessionNotFoundException(Exception e) {
        this("Sessione non valida", e);
    }

    public SessionNotFoundException(String msg) {
        super(msg);
    }
    public SessionNotFoundException(String msg, Exception e) {
        super(msg, e);
    }

}
