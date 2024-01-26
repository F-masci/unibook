package it.ispw.unibook.utils;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.AccountTypes;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;

import java.util.ArrayList;

public class SessionManager {

    private static final ArrayList<AccountEntity> sessions = new ArrayList<>();

    private SessionManager() {}

    public static void addSession(AccountEntity account) {
        sessions.add(account);
    }

    public static AccountEntity getAccountBySessionID(int code) throws SessionNotFoundException {
        for(AccountEntity a: sessions) {
            if(a.getCode() == code) return a;
        }
        throw new SessionNotFoundException();
    }

    public static AccountTypes getAccountTypeBySessionID(int code) throws SessionNotFoundException {
        for(AccountEntity a: sessions) {
            if(a.getCode() == code) return a.getType();
        }
        throw new SessionNotFoundException();
    }

}
