package it.ispw.unibook.exceptions.negotiation;

public class NegotiationException extends Exception {

    private static final String DEFAULT_MSG = "Erorre";

    public NegotiationException() {
        this(DEFAULT_MSG);
    }
    public NegotiationException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public NegotiationException(String msg) {
        super(msg);
    }
    public NegotiationException(String msg, Throwable cause) {
        super(msg, cause);
    }



}
