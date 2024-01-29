package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookBean extends Bean {

    private final String isbn;
    private String name;
    protected String regex = "^\\d{13}$";

    public BookBean(String isbn) throws BookException {
        this(isbn, null);
    }

    public BookBean(String isbn, String name) throws BookException {
        try {
            this.isbn = isbn;
            this.name = name;
            validateISBN();
        } catch(ISBNNotValidException e) {
            throw new BookException(e.getMessage(), e);
        }
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

    protected void validateISBN() throws ISBNNotValidException {
        if(isbn != null) {
            Matcher matcher = Pattern.compile(regex).matcher(this.getISBN());
            if (!matcher.matches()) throw new ISBNNotValidException();
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
