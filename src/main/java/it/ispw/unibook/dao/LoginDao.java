package it.ispw.unibook.dao;

import java.sql.SQLException;

public interface LoginDao {

    public void login(String email, String password) throws SQLException;

}
