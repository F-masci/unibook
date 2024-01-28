package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

public class SellableBookForSaleState extends SellableBookAbstractState {

    @Override
    public void markAsSold(SellableBookStateMachineGoF stateMachineGoF, AccountEntity buyer) {
        SellableBookSoldState state = new SellableBookSoldState(buyer);
        stateMachineGoF.setState(state);
    }

    @Override
    public AccountEntity getBuyer() throws SellableBookNotSoldExceptions {
        throw new SellableBookNotSoldExceptions();
    }
}
