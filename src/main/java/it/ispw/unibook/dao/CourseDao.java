package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;

public interface CourseDao {

    void addBookToCourse(CourseEntity course, BookEntity book);
    void removeBookFromCourse(CourseEntity course, BookEntity book);

    void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook);
    void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook);

}
