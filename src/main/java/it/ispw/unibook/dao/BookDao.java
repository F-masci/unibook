package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;

import java.util.List;

public interface BookDao {

    public List<BookEntity> getCourseBooks(int courseCode);

}
