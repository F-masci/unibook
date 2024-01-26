package it.ispw.unibook.exceptions.book.sellable;

import it.ispw.unibook.exceptions.book.BookException;

public class SellableBookException extends BookException {

    public SellableBookException() {
        this("Errore");
    }

    public SellableBookException(String msg) {
        super(msg);
    }

}
