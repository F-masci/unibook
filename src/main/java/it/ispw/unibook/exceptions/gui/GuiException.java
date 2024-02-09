package it.ispw.unibook.exceptions.gui;

public class GuiException extends RuntimeException {

    private static final String DEFAULT_MSG = "Errore";

    public GuiException() {
        this(DEFAULT_MSG);
    }
    public GuiException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public GuiException(String msg) {
        super(msg);
    }
    public GuiException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
