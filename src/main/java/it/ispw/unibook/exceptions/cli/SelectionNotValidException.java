package it.ispw.unibook.exceptions.cli;

public class SelectionNotValidException extends CliException {

    private static final String DEFAULT_MSG = "Selezione non valida";

    public SelectionNotValidException() {
        this(DEFAULT_MSG);
    }
    public SelectionNotValidException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public SelectionNotValidException(String msg) {
        super(msg);
    }
    public SelectionNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
