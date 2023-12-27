package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.book.ISBNNotValidException;

public class ManageBookBean extends BookBean {

    private final int course;

    public ManageBookBean(int course, String ISBN, String name) throws ISBNNotValidException {
        super(ISBN, name);
        this.course = course;
    }
    public ManageBookBean(int course, String ISBN) throws ISBNNotValidException {
        super(ISBN);
        this.course = course;
    }

    public ManageBookBean(int course, BookBean bean) throws ISBNNotValidException {
        this(course, bean.getISBN(), bean.getName());
    }

    public int getCourse() {
        return course;
    }

}
