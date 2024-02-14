package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookException extends Exception {

    private static final String DEFAULT_MSG = "Errore";

    public SellableBookException() {
        this(DEFAULT_MSG);
    }
    public SellableBookException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SellableBookException(String msg) {
        super(msg);
    }
    public SellableBookException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
