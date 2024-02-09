package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.PriceNotValidException;

public class SellableBookBean extends BookBean {

    private final int code;
    private final int courseCode;
    private final Float price;

    public SellableBookBean(int code) throws FieldNotValidException {
        this(code, 0, null, null, null);
    }

    public SellableBookBean(int courseCode, String isbn, Float price) throws FieldNotValidException {
        this(0, courseCode, isbn, null, price);
    }

    public SellableBookBean(int code, String isbn, String name, Float price) throws FieldNotValidException {
        this(code, 0, isbn, name, price);
    }

    public SellableBookBean(int code, int courseCode, String isbn, String name, Float price) throws FieldNotValidException {
        super(isbn, name);
        this.code = code;
        this.courseCode = courseCode;
        this.price = price;
        validatePrice();
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

    private void validatePrice() throws FieldNotValidException {
        if(price != null && price < 0) throw new PriceNotValidException(new PriceNotValidException());
    }

    @Override
    public String toString() {
        return this.getName() + " - " + String.format("%.2f", this.getPrice()) + "€";
    }
}
