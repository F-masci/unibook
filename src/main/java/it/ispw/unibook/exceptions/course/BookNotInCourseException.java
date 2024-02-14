package it.ispw.unibook.exceptions.course;

public class BookNotInCourseException extends CourseException {

    private static final String DEFAULT_MSG = "Il libro non è presente nel corso";

    public BookNotInCourseException() {
        this(DEFAULT_MSG);
    }
    public BookNotInCourseException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public BookNotInCourseException(String msg) {
        super(msg);
    }
    public BookNotInCourseException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
