package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.sellable.PriceNotValidException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;

public class SellableBookBean extends BookBean {

    private final int code;
    private final int courseCode;
    private final Float price;

    public SellableBookBean(int code) throws BookException {
        this(code, 0, null, null, null);
    }

    public SellableBookBean(String isbn) throws BookException {
        this(0, 0, isbn, null, null);
    }

    public SellableBookBean(int code, String isbn, String name, Float price) throws BookException {
        this(code, 0, isbn, name, price);
    }

    public SellableBookBean(int courseCode, BookBean bean, Float price) throws BookException {
        this(0, courseCode, bean.getISBN(), bean.getName(), price);
    }

    public SellableBookBean(int courseCode, String isbn, Float price) throws BookException {
        this(0, courseCode, isbn, null, price);
    }

    public SellableBookBean(int code, int courseCode, String isbn, String name, Float price) throws BookException {
        super(isbn, name);
        try {
            this.code = code;
            this.courseCode = courseCode;
            this.price = price;
            validatePrice();
        } catch (PriceNotValidException e) {
            throw (SellableBookException) new PriceNotValidException(e.getMessage()).initCause(e);
        }
    }

    public float getPrice() {
        return price;
    }
    public int getCourse() {
        return courseCode;
    }
    public int getCode() {
        return code;
    }

    private void validatePrice() throws PriceNotValidException {
        if(price != null && price < 0) throw new PriceNotValidException();
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getPrice();
    }
}