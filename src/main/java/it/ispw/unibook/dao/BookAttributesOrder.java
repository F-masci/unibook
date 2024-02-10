package it.ispw.unibook.dao;

public enum BookAttributesOrder {
    COURSE, ISBN, TITLE;
    public int getIndex() {
        return this.ordinal();
    }
}
