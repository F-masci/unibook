package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

public interface SellableBookStateMachine {

    public void markAsSold(AccountEntity buyer) throws SellableBookAlreadySoldException;

    public AccountEntity getBuyer() throws SellableBookNotSoldExceptions;

    public boolean isSold();
    public boolean isForSale();

}
