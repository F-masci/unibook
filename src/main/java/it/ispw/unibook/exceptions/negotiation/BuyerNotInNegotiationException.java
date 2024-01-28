package it.ispw.unibook.exceptions.negotiation;

public class BuyerNotInNegotiationException extends NegotiationException {

    public BuyerNotInNegotiationException() {
        this("Acquirente non presente");
    }
    public BuyerNotInNegotiationException(Throwable cause) {
        this("Acquirente non presente", cause);
    }

    public BuyerNotInNegotiationException(String msg) {
        super(msg);
    }
    public BuyerNotInNegotiationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
