package it.ispw.unibook.exceptions.course;

public class CourseNotOwnedException extends CourseException {

    private static final String DEFAULT_MSG = "Non sei il proprietario del corso";

    public CourseNotOwnedException() {
        this(DEFAULT_MSG);
    }
    public CourseNotOwnedException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public CourseNotOwnedException(String msg) {
        super(msg);
    }
    public CourseNotOwnedException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
