package it.ispw.unibook.exceptions.course;

public class BookAlreadyInCourseException extends CourseException {

    public BookAlreadyInCourseException() {
        this("Il libro è già presente nel corso");
    }

    public BookAlreadyInCourseException(String msg) {
        super(msg);
    }

}
