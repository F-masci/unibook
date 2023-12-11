package it.ispw.unibook.exceptions.cli;

public class CliException extends Exception {

    public CliException() {
        super("Dati inseriti non validi");
    }

    public CliException(String msg) {
        super(msg);
    }

}
