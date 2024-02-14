package it.ispw.unibook.exceptions.book.sellable;

public class UnsellableBookInCourseException extends SellableBookException {

    private static final String DEFAULT_MSG = "Il libro non può essere venduto in questo corso";

    public UnsellableBookInCourseException() {
        this(DEFAULT_MSG);
    }
    public UnsellableBookInCourseException(Throwable cause) {
        this(DEFAULT_MSG, cause);
    }

    public UnsellableBookInCourseException(String msg) {
        super(msg);
    }
    public UnsellableBookInCourseException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
