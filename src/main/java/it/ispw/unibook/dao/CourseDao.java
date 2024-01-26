package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;

import java.util.List;

public interface CourseDao {

    public CourseEntity retrieveCourseByCode(int courseCode);
    public List<CourseEntity> retrieveCoursesByProfessor(int accountCode);
    CourseEntity retrieveCourseBySellableBook(SellableBookEntity sellableBook, AccountEntity seller);
    public List<CourseEntity> retrieveCourses();

}
