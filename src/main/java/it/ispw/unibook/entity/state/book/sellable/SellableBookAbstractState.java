package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

/**
 * Rappresenta uno stato astratto della macchina a stati per il libro in vendita.
 * Espone i metodi che devono essere implementati da ogni stato della macchina
 */
public abstract class SellableBookAbstractState {

    /**
     *
     * @return Stato iniziale della macchina
     */
    public static SellableBookAbstractState getInitialState() {
        return new SellableBookForSaleState();
    }

    /**
     * Imposta lo stato del libro come 'Venduto'
     * @param buyer Acquirente effettivo del libro in vendita
     * @throws SellableBookAlreadySoldException Viene sollevata se il libro è stato già acquistato
     */
    public abstract void markAsSold(SellableBookStateMachineGoF stateMachine, AccountEntity buyer) throws SellableBookAlreadySoldException;

    /**
     *
     * @return Acquirente effettivo del libro in vendita
     * @throws SellableBookNotSoldExceptions Viene sollevata se il libro non è stato ancora venduto
     */
    public abstract AccountEntity getBuyer() throws SellableBookNotSoldExceptions;

    /**
     *
     * @return <i>True</i> se il libro è stato venduto.<br>
     *         <i>False</i> altrimenti.
     */
    public abstract boolean isSold();

    /**
     *
     * @return <i>True</i> se il libro none è ancora stato venduto.<br>
     *         <i>False</i> altrimenti.
     */
    public abstract boolean isForSale();

}
