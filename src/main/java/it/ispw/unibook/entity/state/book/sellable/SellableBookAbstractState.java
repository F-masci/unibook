package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

public abstract class SellableBookAbstractState {

    public static SellableBookAbstractState getInitialState() {
        return new SellableBookForSaleState();
    }

    public abstract void markAsSold(SellableBookStateMachineGoF stateMachine, AccountEntity buyer) throws SellableBookAlreadySoldException;

    public abstract AccountEntity getBuyer() throws SellableBookNotSoldExceptions;

}
