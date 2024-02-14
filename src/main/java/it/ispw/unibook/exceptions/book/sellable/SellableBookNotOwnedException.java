package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookNotOwnedException extends SellableBookException {

    private static final String DEFAULT_MSG = "Il libro inserito non è tra i tuoi libri in vendita";

    public SellableBookNotOwnedException() {
        this(DEFAULT_MSG);
    }
    public SellableBookNotOwnedException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SellableBookNotOwnedException(String msg) {
        super(msg);
    }
    public SellableBookNotOwnedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
