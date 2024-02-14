package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;

import java.util.List;

public interface AccountDao {

    /**
     * Cerca un account attraverso il codice
     * @param code Codice dell'account da cercare
     * @return Account trovato
     * @throws AccountNotFoundException Viene sollevata se non è stato trovato alcun account
     */
    AccountEntity retrieveAccountByCode(int code) throws AccountNotFoundException;

    /**
     * Cerca la lista degli acquirenti in un libro in vendita
     * @param sellableBook Libro in vendita
     * @return Lista degli acquirenti del libro in vendita
     */
    List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook);

}
