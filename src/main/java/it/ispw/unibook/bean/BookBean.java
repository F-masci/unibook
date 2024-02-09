package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.ISBNNotValidException;

public class BookBean extends Bean {

    private final String isbn;
    private String name;
    protected String regex = "^\\d{13}$";

    public BookBean(String isbn) throws FieldNotValidException {
        this(isbn, null);
    }

    public BookBean(String isbn, String name) throws FieldNotValidException {
        this.isbn = isbn;
        this.name = name;
        validateISBN();
    }

    public String getISBN() {
        return isbn;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name =  name;
    }

    protected void validateISBN() throws FieldNotValidException {
        try {
            if(isbn != null) {
                validateField(this.getISBN(), ISBN_REGEX);
            }
        } catch (FieldNotValidException e) {
            throw new ISBNNotValidException(new ISBNNotValidException());
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
