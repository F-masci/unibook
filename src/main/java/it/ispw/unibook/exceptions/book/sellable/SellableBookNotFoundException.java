package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookNotFoundException extends SellableBookException {

    public SellableBookNotFoundException() {
        this("Il libro non è in vendita nel corso");
    }

    public SellableBookNotFoundException(Exception e) {
        this("Il libro non è in vendita nel corso", e);
    }

    public SellableBookNotFoundException(String msg) {
        super(msg);
    }

    public SellableBookNotFoundException(String msg, Exception e) {
        super(msg, e);
    }

}
