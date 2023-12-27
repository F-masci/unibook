package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;

import java.util.List;

public interface BookDao {

    public List<BookEntity> getCourseBooks(int course);
    public void insertBook(int course, BookEntity book);
    public void removeBookByISBN(int course, String ISBN);

}
