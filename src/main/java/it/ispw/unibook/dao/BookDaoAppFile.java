package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;

import java.util.ArrayList;
import java.util.List;

public class BookDaoAppFile implements BookDao {

    @Override
    public List<BookEntity> retrieveCourseBooks(CourseEntity course) {
        return new ArrayList<>();
    }

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        // TODO
    }

    @Override
    public void removeBookFromCourse(CourseEntity course, BookEntity book) {
        // TODO
    }
}
