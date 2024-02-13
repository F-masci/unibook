package it.ispw.unibook.dao;

public enum NegotiationAttributesOrder {
    BOOK, STUDENT;
    public int getIndex() {
        return this.ordinal();
    }

}
