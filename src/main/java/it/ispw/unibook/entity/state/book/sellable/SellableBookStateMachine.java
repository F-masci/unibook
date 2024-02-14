package it.ispw.unibook.entity.state.book.sellable;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;

/**
 * Definisce le operazioni che deve esporre una macchina a stati per
 * rappresentare lo stato interno di un libro in vendita
 */
public interface SellableBookStateMachine {

    /**
     * Imposta lo stato del libro come 'Venduto'
     * @param buyer Acquirente effettivo del libro in vendita
     * @throws SellableBookAlreadySoldException Viene sollevata se il libro è stato già acquistato
     */
    void markAsSold(AccountEntity buyer) throws SellableBookAlreadySoldException;

    /**
     *
     * @return Acquirente effettivo del libro in vendita
     * @throws SellableBookNotSoldExceptions Viene sollevata se il libro non è stato ancora venduto
     */
    AccountEntity getBuyer() throws SellableBookNotSoldExceptions;

    /**
     *
     * @return <i>True</i> se il libro è stato venduto.<br>
     *         <i>False</i> altrimenti.
     */
    boolean isSold();

    /**
     *
     * @return <i>True</i> se il libro none è ancora stato venduto.<br>
     *         <i>False</i> altrimenti.
     */
    boolean isForSale();

}
