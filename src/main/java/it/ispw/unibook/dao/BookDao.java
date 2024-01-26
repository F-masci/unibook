package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;

import java.util.List;

public interface BookDao {

    public List<BookEntity> retrieveCourseBooks(CourseEntity course);
    public void addBookToCourse(CourseEntity course, BookEntity book);

    public void removeBookFromCourse(CourseEntity course, BookEntity book);

}
