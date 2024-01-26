package it.ispw.unibook.exceptions.book.sellable;

public class PriceNotValidException extends SellableBookException {

    public PriceNotValidException() {
        this("Il prezzo non è valido");
    }

    public PriceNotValidException(String msg) {
        super(msg);
    }

}
