package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;

import java.util.List;

public interface NegotiationDao {

    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook);
    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);

}
