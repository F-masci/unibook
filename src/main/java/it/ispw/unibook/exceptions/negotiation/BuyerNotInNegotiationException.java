package it.ispw.unibook.exceptions.negotiation;

public class BuyerNotInNegotiationException extends NegotiationException {

    private static final String DEFAULT_MSG = "Acquirente non presente";

    public BuyerNotInNegotiationException() {
        this(DEFAULT_MSG);
    }
    public BuyerNotInNegotiationException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BuyerNotInNegotiationException(String msg) {
        super(msg);
    }
    public BuyerNotInNegotiationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
