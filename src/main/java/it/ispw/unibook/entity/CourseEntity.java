package it.ispw.unibook.entity;

import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.UnsellableBookInCourseException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.factory.ApplicationDaoFactory;

import java.util.List;

public class CourseEntity {

    private final int code;
    private final String name;
    private final int startYear;
    private final int endYear;
    private List<BookEntity> books = null;
    private List<SellableBookEntity> sellableBooks = null;

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
    public List<SellableBookEntity> getSellableBooks() {
        if(books == null) loadSellableBooks();
        return sellableBooks;
    }

    public void addBook(BookEntity book) throws BookAlreadyInCourseException {
        if(books == null) loadBooks();
        if(books.contains(book)) throw new BookAlreadyInCourseException();
        books.add(book);
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        dao.addBookToCourse(this, book);
    }
    public void removeBook(BookEntity book) throws BookNotInCourseException {
        if(books == null) loadBooks();
        if(!books.contains(book)) throw new BookNotInCourseException();
        books.remove(book);
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        dao.removeBookFromCourse(this, book);
    }

    public void addSellableBook(SellableBookEntity sellableBook) throws UnsellableBookInCourseException {
        if(sellableBooks == null) loadSellableBooks();
        if(books == null) loadBooks();
        if(!books.contains(sellableBook)) throw new UnsellableBookInCourseException();
        sellableBooks.add(sellableBook);
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.addSellableBookToCourse(this, sellableBook);
    }

    public void removeSellableBook(SellableBookEntity sellableBook) throws SellableBookNotFoundException {
        if(sellableBooks == null) loadSellableBooks();
        if(!sellableBooks.contains(sellableBook)) throw new SellableBookNotFoundException();
        sellableBooks.remove(sellableBook);
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.removeSellableBookFromCourse(this, sellableBook);
    }

    private void loadBooks() {
        BookDao dao = ApplicationDaoFactory.getInstance().getBookDao();
        books = dao.retrieveCourseBooks(this);
    }

    private void loadSellableBooks() {
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        sellableBooks = dao.retrieveCourseSellableBooks(this);
    }

}
