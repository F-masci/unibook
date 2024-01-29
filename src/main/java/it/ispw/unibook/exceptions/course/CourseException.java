package it.ispw.unibook.exceptions.course;

public class CourseException extends Exception{

    private final static String DEFAULT_MSG = "Errore";

    public CourseException() {
        this(DEFAULT_MSG);
    }
    public CourseException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public CourseException(String msg) {
        super(msg);
    }
    public CourseException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
