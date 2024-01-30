package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookException extends Exception {

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
