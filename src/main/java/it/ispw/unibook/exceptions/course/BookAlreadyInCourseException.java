package it.ispw.unibook.exceptions.course;

public class BookAlreadyInCourseException extends CourseException {

    private static final String DEFAULT_MSG = "Il libro è già presente nel corso";

    public BookAlreadyInCourseException() {
        this(DEFAULT_MSG);
    }
    public BookAlreadyInCourseException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BookAlreadyInCourseException(String msg) {
        super(msg);
    }
    public BookAlreadyInCourseException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
