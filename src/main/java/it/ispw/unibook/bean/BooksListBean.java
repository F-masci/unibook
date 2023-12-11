package it.ispw.unibook.bean;

import java.util.List;

public class BooksListBean extends Bean {

    private List<BookBean> books;
    private final int courseCode;

    public BooksListBean(int courseCode) {
        this.courseCode = courseCode;
    }

    public void setList(List<BookBean> books) {
        this.books = books;
    }
    public List<BookBean> getList() {
        return books;
    }

    public int getCourseCode() {
        return courseCode;
    }

}
