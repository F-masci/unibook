package it.ispw.unibook.exceptions.negotiation;

public class BuyerAlreadyInNegotiationException extends NegotiationException {

    private static final String DEFAULT_MSG = "Acquirente già presente";

    public BuyerAlreadyInNegotiationException() {
        this(DEFAULT_MSG);
    }
    public BuyerAlreadyInNegotiationException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BuyerAlreadyInNegotiationException(String msg) {
        super(msg);
    }
    public BuyerAlreadyInNegotiationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
