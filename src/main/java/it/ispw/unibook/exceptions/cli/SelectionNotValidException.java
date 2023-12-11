package it.ispw.unibook.exceptions.cli;

public class SelectionNotValidException extends CliException {

    public SelectionNotValidException() {
        super("Selezione non valida");
    }

    public SelectionNotValidException(String msg) {
        super(msg);
    }

}
