package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.exceptions.book.BookNotFoundException;

public interface LibraryDao {

    public BookEntity getBookByISBN(String ISBN) throws BookNotFoundException;

}
