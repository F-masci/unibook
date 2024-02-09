package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;

public interface LoginDao {

    /**
     * Permette di eseguire l'accesso al sistema
     * @param email Email dell'utente
     * @param password Password dell'utente
     * @return L'account relativo all'utente che ha eseguito l'accesso
     * @throws EmailNotFoundException Viene sollevata in caso non ci sia alcun account con l'email inserita
     * @throws IncorrectPasswordException Viene sollevata in caso la password sia errata
     */
    AccountEntity login(String email, String password) throws EmailNotFoundException, IncorrectPasswordException;

}
