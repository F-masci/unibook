package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;

import java.util.List;

public interface CourseDao {

    public List<CourseEntity> getAllCourses();
    public List<CourseEntity> getProfessorCourses(int accountCode);

}
