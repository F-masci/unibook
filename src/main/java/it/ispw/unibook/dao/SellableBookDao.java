package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;

import java.util.List;

public interface SellableBookDao {

    SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException;
    List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn);
    List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity seller);
    List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer);
    List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course);

    void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);
    void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);
    void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer);

}
