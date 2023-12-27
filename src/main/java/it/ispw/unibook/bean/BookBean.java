package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.book.ISBNNotValidException;
import it.ispw.unibook.exceptions.login.EmailNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookBean {

    private final String ISBN;
    private String name;
    protected String regex = "^\\d{13}$";

    public BookBean(String ISBN) throws ISBNNotValidException {
        this(ISBN, null);
    }

    public BookBean(String ISBN, String name) throws ISBNNotValidException {
        this.ISBN = ISBN;
        this.name = name;
        validateISBN();
    }

    public String getISBN() {
        return ISBN;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name =  name;
    }

    protected void validateISBN() throws ISBNNotValidException {
        Matcher matcher = Pattern.compile(regex).matcher(this.getISBN());
        if(!matcher.matches()) throw new ISBNNotValidException();
    }

}
