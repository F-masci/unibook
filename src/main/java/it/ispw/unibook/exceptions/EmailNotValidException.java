package it.ispw.unibook.exceptions;

public class EmailNotValidException extends FieldNotValidException {

    public EmailNotValidException() {
        this("Email non valida");
    }
    public EmailNotValidException(Throwable cause) {
        this("Email non valida", cause);
    }

    public EmailNotValidException(String msg) {
        super(msg);
    }
    public EmailNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
