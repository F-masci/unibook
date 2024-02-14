package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

/**
 * Implementazione della macchina a stati per rappresentare lo stato interno di un libro in vendita
 * che utilizza il pattern GoF State
 */
public class SellableBookStateMachineGoF implements SellableBookStateMachine {

    // Stato corrente della macchina
    private SellableBookAbstractState current;

    /**
     * Costruttore di default.<br>
     * Lo stato iniziale è quello 'In vendita'
     */
    public SellableBookStateMachineGoF() {
        current = SellableBookAbstractState.getInitialState();
    }

    /**
     * Modifica lo stato corrente della macchina
     * @param state Nuovo stato da impostare
     */
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
