package it.ispw.unibook.exceptions.course;

public class CourseException extends Exception{

    public CourseException() {
        this("Errore durante la manipolazione del corso");
    }

    public CourseException(String msg) {
        super(msg);
    }

}
