package it.ispw.unibook.bean;

import java.util.List;

public class CoursesListBean extends Bean {

    private List<CourseBean> courses;

    public void setList(List<CourseBean> courses) {
        this.courses = courses;
    }
    public List<CourseBean> getList() {
        return courses;
    }
}
