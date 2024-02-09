package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

public class SellableBookStateMachineGoF implements SellableBookStateMachine {

    private SellableBookAbstractState current;

    public SellableBookStateMachineGoF() {
        current = SellableBookAbstractState.getInitialState();
    }

    public void setState(SellableBookAbstractState state) {
        this.current = state;
    }

    @Override
    public void markAsSold(AccountEntity buyer) throws SellableBookAlreadySoldException {
        current.markAsSold(this, buyer);
    }

    @Override
    public AccountEntity getBuyer() throws SellableBookNotSoldExceptions {
        return current.getBuyer();
    }

    @Override
    public boolean isSold() {
        return current.isSold();
    }

    @Override
    public boolean isForSale() {
        return current.isForSale();
    }
}
