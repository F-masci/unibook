package it.ispw.unibook.dao;

import it.ispw.unibook.entity.CourseEntity;

import java.util.List;

public interface CourseDao {

    public CourseEntity retrieveCourseByCode(int courseCode);
    public List<CourseEntity> retrieveCoursesByProfessor(int accountCode);

    public List<CourseEntity> retrieveCourses();

}
