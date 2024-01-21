package it.ispw.unibook.exceptions.course;

public class BookNotInCourseException extends CourseException {

        public BookNotInCourseException() {
            this("Il libro non è presente nel corso");
        }

        public BookNotInCourseException(String msg) {
            super(msg);
        }


}
