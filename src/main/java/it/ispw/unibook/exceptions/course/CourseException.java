package it.ispw.unibook.exceptions.course;

public class CourseException extends Exception{

    public CourseException() {
        this("Errore durante la manipolazione del corso");
    }
    public CourseException(Exception e) {
        this("Errore durante la manipolazione del corso", e);
    }

    public CourseException(String msg) {
        super(msg);
    }
    public CourseException(String msg, Exception e) {
        super(msg, e);
    }

}
