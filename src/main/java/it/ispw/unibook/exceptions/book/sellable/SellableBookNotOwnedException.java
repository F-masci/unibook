package it.ispw.unibook.exceptions.book.sellable;

public class SellableBookNotOwnedException extends SellableBookException {

    public SellableBookNotOwnedException() {
        this("Il libro inserito non è tra i tuoi libri in vendita");
    }

    public SellableBookNotOwnedException(String msg) {
        super(msg);
    }

}
