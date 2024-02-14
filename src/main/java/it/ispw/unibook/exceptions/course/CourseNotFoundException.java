package it.ispw.unibook.exceptions.course;

public class CourseNotFoundException extends CourseException {

    private static final String DEFAULT_MSG = "Corso non trovato";

    public CourseNotFoundException() {
        this(DEFAULT_MSG);
    }
    public CourseNotFoundException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public CourseNotFoundException(String msg) {
        super(msg);
    }
    public CourseNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
