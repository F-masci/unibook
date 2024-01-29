package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookNotSoldExceptions extends SellableBookException{

    private static final String DEFAULT_MSG = "Libro non ancora venduto";

    public SellableBookNotSoldExceptions() {
        this(DEFAULT_MSG);
    }
    public SellableBookNotSoldExceptions(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SellableBookNotSoldExceptions(String msg) {
        super(msg);
    }
    public SellableBookNotSoldExceptions(String msg, Throwable cause) {
        super(msg, cause);
    }

}
