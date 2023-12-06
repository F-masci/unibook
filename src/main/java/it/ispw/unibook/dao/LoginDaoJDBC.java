package it.ispw.unibook.dao;

import it.ispw.unibook.controller.grafici.cli.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class LoginDaoJDBC implements LoginDao {

    private Connection connection = null;

    public LoginDaoJDBC() {
        connection = ConnectionJDBC.getInstance();
    }

    @Override
    public void login(String email, String password) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM account WHERE email=? AND password=?");
        stm.setString(1, email);
        stm.setString(2, password);
        ResultSet res = stm.executeQuery();

        Printer.println(res.getString("email") + " -> " + res.getString("password"));
    }
}
