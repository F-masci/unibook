package it.ispw.unibook.exceptions.negotiation;

public class NegotiationException extends Exception {

    public NegotiationException() {
        this("Erorre");
    }
    public NegotiationException(Throwable e) {
        this("Erorre", e);
    }

    public NegotiationException(String msg) {
        super(msg);
    }
    public NegotiationException(String msg, Throwable e) {
        super(msg, e);
    }



}
