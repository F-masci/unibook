package it.ispw.unibook.dao;

public enum SellableBookAttributesOrder {
    CODE, COURSE, ISBN, SELLER, PRICE, BUYER;
    public int getIndex() {
        return this.ordinal();
    }
}
