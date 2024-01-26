package it.ispw.unibook.exceptions.book.sellable;



public class UnsellableBookInCourseException extends SellableBookException {

    public UnsellableBookInCourseException() {
        this("Il libro non può essere venduto in questo corso");
    }

    public UnsellableBookInCourseException(String msg) {
        super(msg);
    }

}
