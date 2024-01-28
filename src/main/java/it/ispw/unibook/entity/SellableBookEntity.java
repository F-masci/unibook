package it.ispw.unibook.entity;

import it.ispw.unibook.dao.AccountDao;
import it.ispw.unibook.entity.state.book.sellable.SellableBookStateMachine;
import it.ispw.unibook.entity.state.book.sellable.SellableBookStateMachineGoF;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;
import it.ispw.unibook.exceptions.negotiation.BuyerAlreadyInNegotiationException;
import it.ispw.unibook.exceptions.negotiation.BuyerNotInNegotiationException;
import it.ispw.unibook.factory.ApplicationDaoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellableBookEntity extends BookEntity {

    private int code;

    private final float price;

    private final AccountEntity seller;

    private List<AccountEntity> buyers = null;

    private SellableBookStateMachine state = new SellableBookStateMachineGoF(this);

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

    public List<AccountEntity> getBuyers() {
        if(buyers == null) loadNegotiations();
        return buyers;
    }

    public void clearBuyers() {
        List<AccountEntity> buyers = new ArrayList<>(this.getBuyers());
        for(AccountEntity b: buyers) {
            try {
                this.removeBuyer(b);
            } catch(BuyerNotInNegotiationException ignored) {}
        }
    }

    public void addBuyer(AccountEntity buyer) throws BuyerAlreadyInNegotiationException {
        loadNegotiations();
        if(buyers.contains(buyer)) throw new BuyerAlreadyInNegotiationException();
        buyers.add(buyer);
        // TODO: capire se usare DAO account o DAO SellableBook
        AccountDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
        dao.addBuyerToSellableBookNegotiation(this, buyer);
    }

    public void removeBuyer(AccountEntity buyer) throws BuyerNotInNegotiationException {
        loadNegotiations();
        if(!buyers.contains(buyer)) throw new BuyerNotInNegotiationException();
        buyers.add(buyer);
        // TODO: capire se usare DAO account o DAO SellableBook
        AccountDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
        dao.removeBuyerFromSellableBookNegotiation(this, buyer);
    }

    public void markAsSold(AccountEntity buyer) throws SellableBookAlreadySoldException {
        state.markAsSold(buyer);
        AccountDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
        dao.setBuyerToSellableBook(this, buyer);
    }
    public AccountEntity getBuyer() throws SellableBookNotSoldExceptions {
        return state.getBuyer();
    }

    private void loadNegotiations() {
        if(buyers == null) {
            AccountDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
            buyers = dao.retrieveBuyersBySellableBook(this);
        }
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
