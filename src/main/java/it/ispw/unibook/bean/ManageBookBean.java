package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;

public class ManageBookBean extends BookBean {

    private final int course;

    public ManageBookBean(int course, String isbn, String name) throws BookException {
        super(isbn, name);
        this.course = course;
    }
    public ManageBookBean(int course, String isbn) throws BookException {
        super(isbn);
        this.course = course;
    }

    public ManageBookBean(int course, BookBean bean) throws BookException {
        this(course, bean.getISBN(), bean.getName());
    }

    public int getCourse() {
        return course;
    }

}
