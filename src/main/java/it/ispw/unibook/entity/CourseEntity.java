package it.ispw.unibook.entity;

import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CourseEntity {

    private final int code;
    private final String name;
    private final int startYear;
    private final int endYear;
    private List<BookEntity> books = null;
    private final List<BookEntity> toAdd = new ArrayList<>();
    private final List<BookEntity> toDel = new ArrayList<>();

    public CourseEntity(int code) {
        this(code, null, 0, 0);
    }

    public CourseEntity(int code, String name, int startYear, int endYear) {
        this.code = code;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getStartYear() {
        return startYear;
    }
    public int getEndYear() {
        return endYear;
    }
    public List<BookEntity> getBooks() {
        if(books == null) loadBooks();
        return books;
    }

    public List<BookEntity> getAddedBooks() {
        return toAdd;
    }
    public List<BookEntity> getDeletedBooks() {
        return toDel;
    }

    public void addBook(BookEntity book) throws BookAlreadyInCourseException {
        if(books == null) loadBooks();
        if(books.contains(book)) throw new BookAlreadyInCourseException();
        books.add(book);
        toAdd.add(book);
    }
    public void delBook(BookEntity book) throws BookNotInCourseException {
        if(books == null) loadBooks();
        if(!books.contains(book)) throw new BookNotInCourseException();
        books.remove(book);
        toDel.add(book);
    }

    public void saveBooks() {
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        dao.saveCourseBooks(this);
        toAdd.clear();
        toDel.clear();
    }

    private void loadBooks() {
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        books = dao.retrieveCourseBooks(this);
    }

}
