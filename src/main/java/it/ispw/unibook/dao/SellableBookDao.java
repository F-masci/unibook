package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;

import java.util.List;

public interface SellableBookDao {

    public SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException;
    public List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn);
    public List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity seller);
    public List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer);
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course);

    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);
    public void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer);

}
