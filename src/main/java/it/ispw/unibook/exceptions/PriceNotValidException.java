package it.ispw.unibook.exceptions;

import it.ispw.unibook.exceptions.FieldNotValidException;

public class PriceNotValidException extends FieldNotValidException {

    private static final String DEFAULT_MSG = "Il prezzo non è valido";

    public PriceNotValidException() {
        this(DEFAULT_MSG);
    }
    public PriceNotValidException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public PriceNotValidException(String msg) {
        super(msg);
    }
    public PriceNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
