package it.ispw.unibook.bean;

public class BookBean {

    private final String ISBN;
    private final String name;

    public BookBean(String ISBN, String name) {
        this.ISBN = ISBN;
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }
    public String getName() {
        return name;
    }

}
