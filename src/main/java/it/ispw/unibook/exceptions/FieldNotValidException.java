package it.ispw.unibook.exceptions;

public class FieldNotValidException extends Exception {

    public FieldNotValidException() {
        this("Campo non valido");
    }
    public FieldNotValidException(Throwable cause) {
        this("Campo non valido", cause);
    }

    public FieldNotValidException(String msg) {
        super(msg);
    }
    public FieldNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
