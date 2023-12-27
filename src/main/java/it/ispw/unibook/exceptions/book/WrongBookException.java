package it.ispw.unibook.exceptions.book;

public class WrongBookException extends BookException {

    public WrongBookException(String msg) {
        super(msg);
    }

    public WrongBookException() {
        this("Libro errato");
    }

}
