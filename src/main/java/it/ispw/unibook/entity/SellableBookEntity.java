package it.ispw.unibook.entity;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.factory.SellableBookDaoFactory;

import java.util.Objects;

public class SellableBookEntity extends BookEntity {

    private int code;

    private final float price;

    private final AccountEntity seller;

    // TODO: usare factory
    public SellableBookEntity(int code) {
        this(code, null, null, -1, null);
    }

    public SellableBookEntity(String isbn, float price, AccountEntity seller) {
        this(isbn, null, price, seller);
    }

    public SellableBookEntity(String isbn, String title, float price, AccountEntity seller) {
        this(0, isbn, title, price, seller);
    }

    public SellableBookEntity(int code, String isbn, String title, float price, AccountEntity seller) {
        super(isbn, title);
        this.code = code;
        this.price = price;
        this.seller = seller;
    }

    public float getPrice() {
        return price;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public AccountEntity getSeller() {
        return seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SellableBookEntity) || ((SellableBookEntity) o).getCode() == 0) return false;
        return  Objects.equals( ((SellableBookEntity) o).getCode(), this.getCode() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode());
    }

}
