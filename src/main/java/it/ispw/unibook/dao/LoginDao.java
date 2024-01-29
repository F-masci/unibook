package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;

public interface LoginDao {

    public AccountEntity login(String email, String password) throws EmailNotFoundException, IncorrectPasswordException;

}
