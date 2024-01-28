package it.ispw.unibook.exceptions.book.sellable;

import it.ispw.unibook.exceptions.book.BookException;

public class SellableBookException extends BookException {

    public SellableBookException() {
        this("Errore");
    }
    public SellableBookException(Throwable cause) {
        this("Errore", cause);
    }

    public SellableBookException(String msg) {
        super(msg);
    }
    public SellableBookException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
