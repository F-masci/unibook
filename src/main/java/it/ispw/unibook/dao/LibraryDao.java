package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;

public interface LibraryDao {

    public BookEntity getBookByISBN(String ISBN);

}
