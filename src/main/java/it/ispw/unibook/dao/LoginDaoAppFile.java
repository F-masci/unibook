package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;

public class LoginDaoAppFile implements LoginDao {
    @Override
    public AccountEntity login(String email, String password) throws EmailNotFoundException, IncorrectPasswordException {
        return null;
    }
}
