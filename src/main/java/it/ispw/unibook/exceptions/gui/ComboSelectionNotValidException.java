package it.ispw.unibook.exceptions.gui;

public class ComboSelectionNotValidException extends GuiException {

    private static final String DEFAULT_MSG = "Selezione non valida";

    public ComboSelectionNotValidException() {
        this(DEFAULT_MSG);
    }
    public ComboSelectionNotValidException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public ComboSelectionNotValidException(String msg) {
        super(msg);
    }
    public ComboSelectionNotValidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
