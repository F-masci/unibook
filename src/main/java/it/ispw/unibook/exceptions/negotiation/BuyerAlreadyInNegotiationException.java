package it.ispw.unibook.exceptions.negotiation;

public class BuyerAlreadyInNegotiationException extends NegotiationException {

    public BuyerAlreadyInNegotiationException() {
        this("Acquirente già presente");
    }
    public BuyerAlreadyInNegotiationException(Throwable e) {
        this("Acquirente già presente", e);
    }

    public BuyerAlreadyInNegotiationException(String msg) {
        super(msg);
    }
    public BuyerAlreadyInNegotiationException(String msg, Throwable e) {
        super(msg, e);
    }

}
