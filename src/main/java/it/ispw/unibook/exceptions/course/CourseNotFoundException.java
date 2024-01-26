package it.ispw.unibook.exceptions.course;

public class CourseNotFoundException extends CourseException {

    public CourseNotFoundException() {
        this("Corso non trovato");
    }

    public CourseNotFoundException(String msg) {
        super(msg);
    }

}
