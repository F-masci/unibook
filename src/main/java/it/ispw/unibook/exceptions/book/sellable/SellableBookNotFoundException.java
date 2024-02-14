package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookNotFoundException extends SellableBookException {

    private static final String DEFAULT_MSG = "Il libro non è in vendita nel corso";

    public SellableBookNotFoundException() {
        this(DEFAULT_MSG);
    }
    public SellableBookNotFoundException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SellableBookNotFoundException(String msg) {
        super(msg);
    }
    public SellableBookNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
