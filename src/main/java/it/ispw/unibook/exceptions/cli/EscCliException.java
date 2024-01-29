package it.ispw.unibook.exceptions.cli;

public class EscCliException extends CliException {

    private static final String DEFAULT_MSG = "Hai inserito esc";

    public EscCliException() {
        this(DEFAULT_MSG);
    }
    public EscCliException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public EscCliException(String msg) {
        super(msg);
    }
    public EscCliException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
