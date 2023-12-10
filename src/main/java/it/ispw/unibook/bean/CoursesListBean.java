package it.ispw.unibook.bean;

import it.ispw.unibook.entity.CourseEntity;

import java.util.List;

public class CoursesListBean {

    private final List<CourseEntity> courses;
    private int pos = 0;
    private CourseEntity current;

    public CoursesListBean(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public boolean first() {
        if(courses.isEmpty()) return false;
        pos = 0;
        current = courses.get(pos);
        return true;
    }

    public boolean hasNext() {
        return pos < courses.size();
    }

    public boolean next() {
        pos++;
        if(pos == courses.size()) return false;
        current = courses.get(pos);
        return true;
    }

    public String getName() {
        return current.getName();
    }

    public String getCode() {
        return current.getName();
    }

}
