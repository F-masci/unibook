package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookAlreadySoldException extends SellableBookException {

    private static final String DEFAULT_MSG = "Libro già venduto";

    public SellableBookAlreadySoldException() {
        this(DEFAULT_MSG);
    }
    public SellableBookAlreadySoldException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SellableBookAlreadySoldException(String msg) {
        super(msg);
    }
    public SellableBookAlreadySoldException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
