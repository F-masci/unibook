package it.ispw.unibook.exceptions.negotiation;

public class BuyerIsSellerException extends NegotiationException {

    private static final String DEFAULT_MSG = "L'acquirente è il venditore del libro";

    public BuyerIsSellerException() {
        this(DEFAULT_MSG);
    }
    public BuyerIsSellerException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BuyerIsSellerException(String msg) {
        super(msg);
    }
    public BuyerIsSellerException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
