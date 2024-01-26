package it.ispw.unibook.exceptions.login;

public class SessionException extends Exception {

    public SessionException() {
        this("Errore");
    }
    public SessionException(Exception e) {
        this("Errore", e);
    }

    public SessionException(String msg) {
        super(msg);
    }
    public SessionException(String msg, Exception e) {
        super(msg, e);
    }

}
