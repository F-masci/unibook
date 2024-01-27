package it.ispw.unibook.entity;

import it.ispw.unibook.dao.NegotiationDao;
import it.ispw.unibook.exceptions.negotiation.BuyerAlreadyInNegotiationException;
import it.ispw.unibook.factory.ApplicationDaoFactory;

import java.util.List;
import java.util.Objects;

public class SellableBookEntity extends BookEntity {

    private int code;

    private final float price;

    private final AccountEntity seller;

    private List<AccountEntity> buyers = null;

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

    public void addBuyer(AccountEntity buyer) throws BuyerAlreadyInNegotiationException {
        if(buyers == null) loadNegotiations();
        if(buyers.contains(buyer)) throw new BuyerAlreadyInNegotiationException();
        buyers.add(buyer);
        // TODO: capire se usare DAO account o DAO SellableBook
        NegotiationDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
        dao.addBuyerToSellableBookNegotiation(this, buyer);
    }

    private void loadNegotiations() {
        NegotiationDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
        buyers = dao.retrieveBuyersBySellableBook(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof BookEntity book && Objects.equals(book.getISBN(), this.getISBN())) return true;
        if (!(o instanceof SellableBookEntity sellableBook) || sellableBook.getCode() == 0) return false;
        return  Objects.equals( ((SellableBookEntity) o).getCode(), this.getCode() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode());
    }

}
