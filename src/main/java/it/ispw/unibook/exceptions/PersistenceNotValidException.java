package it.ispw.unibook.exceptions;

public class PersistenceNotValidException extends Exception {

    public PersistenceNotValidException(String msg) {
        super(msg);
    }

    public PersistenceNotValidException() {
        this("Persistenza selezionata non valida");
    }

}
