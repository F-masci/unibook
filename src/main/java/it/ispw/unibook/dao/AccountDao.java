package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;

import java.util.List;

public interface AccountDao {

    public AccountEntity retrieveAccountByCode(int code) throws AccountNotFoundException;
    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook);

}
