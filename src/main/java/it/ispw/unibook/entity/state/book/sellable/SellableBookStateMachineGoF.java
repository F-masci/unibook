package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

public class SellableBookStateMachineGoF implements SellableBookStateMachine {

    private final SellableBookEntity reference;
    private SellableBookAbstractState current;

    public SellableBookStateMachineGoF(SellableBookEntity reference) {
        this.reference = reference;
        current = SellableBookAbstractState.getInitialState(reference);
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
}
