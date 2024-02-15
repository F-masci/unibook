package it.ispw.unibook.exceptions.book.sellable;

public class BuyerNotFoundInSellableBookException extends SellableBookException {

    private static final String DEFAULT_MSG = "L'acquirente selezionato non è valido";

    public BuyerNotFoundInSellableBookException() {
        this(DEFAULT_MSG);
    }
    public BuyerNotFoundInSellableBookException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BuyerNotFoundInSellableBookException(String msg) {
        super(msg);
    }
    public BuyerNotFoundInSellableBookException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
