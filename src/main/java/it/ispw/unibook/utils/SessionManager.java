package it.ispw.unibook.utils;

import it.ispw.unibook.entity.AccountEntity;

import java.util.ArrayList;

public class SessionManager {

    private static final ArrayList<AccountEntity> sessions = new ArrayList<>();

    private SessionManager() {}

    public static void addSession(AccountEntity account) {
        sessions.add(account);
    }

    public static AccountEntity getAccountBySessionID(int code) {
        for(AccountEntity a: sessions) {
            if(a.getCode() == code) return a;
        }
        return null;
    }

}
