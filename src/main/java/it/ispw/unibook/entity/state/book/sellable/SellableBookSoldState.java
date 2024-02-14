package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;

/**
 * Implementa lo stato 'Venduto' per la macchina a stati del libro in vendita
 */
public class SellableBookSoldState extends SellableBookAbstractState {

    // Acquirente effettivo del libro in vendita
    private final AccountEntity buyer;

    /**
     * Costruttore di default per l'entità
     * @param buyer Acquirente effettivo del libro in vendita
     */
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
