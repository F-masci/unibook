package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;

import java.util.List;

public interface AccountDao {

    public AccountEntity retrieveAccountByCode(int code);
    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook);
    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);
    public void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer);

}
