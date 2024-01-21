package it.ispw.unibook.exceptions;

public class EmailNotValidException extends Exception {

    public EmailNotValidException() {
        this("Email non valida");
    }

    public EmailNotValidException(String msg) {
        super(msg);
    }

}
