package it.ispw.unibook.dao;

public enum AccountAttributesOrder {

    CODE, EMAIL, PASSWORD, NAME, SURNAME, TYPE;

    public int getIndex() {
        return this.ordinal();
    }

}
