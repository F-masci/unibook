package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;

public class SellableBookSoldState extends SellableBookAbstractState {

    private final AccountEntity buyer;

    public SellableBookSoldState(AccountEntity buyer) {
        this.buyer = buyer;
    }

    @Override
    public void markAsSold(SellableBookStateMachineGoF stateMachine, AccountEntity buyer) throws SellableBookAlreadySoldException {
        throw new SellableBookAlreadySoldException();
    }

    @Override
    public AccountEntity getBuyer() {
        return buyer;
    }

    @Override
    public boolean isSold() {
        return true;
    }

    @Override
    public boolean isForSale() {
        return false;
    }
}
