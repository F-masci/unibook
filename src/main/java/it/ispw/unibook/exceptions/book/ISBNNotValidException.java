package it.ispw.unibook.exceptions.book;

public class ISBNNotValidException extends BookException {

    public ISBNNotValidException(String msg) {
        super(msg);
    }

    public ISBNNotValidException() {
        this("isbn non valido");
    }

}
