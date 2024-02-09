package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;

public interface CourseDao {

    public void addBookToCourse(CourseEntity course, BookEntity book);
    public void removeBookFromCourse(CourseEntity course, BookEntity book);

    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook);
    public void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook);

}
