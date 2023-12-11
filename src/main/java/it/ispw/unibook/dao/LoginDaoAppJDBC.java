package it.ispw.unibook.dao;

import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.AccountTypes;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.ConnectionAppJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class LoginDaoAppJDBC implements LoginDao {

    private Connection connection = null;

    public LoginDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    @Override
    public AccountEntity login(String email, String password) throws EmailNotFoundException, IncorrectPasswordException {

        AccountEntity account = null;

        try {

            // Cerco l'utente
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM account WHERE email=? AND password=? LIMIT 1");
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet res = stm.executeQuery();

            // Cerco di capire l'erorre
            if (!res.first()) {
                stm = connection.prepareStatement("SELECT * FROM account WHERE email=? LIMIT 1");
                stm.setString(1, email);
                res = stm.executeQuery();

                if (!res.first()) {
                    res.close();
                    throw new EmailNotFoundException();
                } else {
                    res.close();
                    throw new IncorrectPasswordException();
                }

            }

            AccountTypes type = switch (res.getString("type")) {
                case "Professore" -> AccountTypes.PROFESSOR;
                case "Studente" -> AccountTypes.STUDENT;
                default -> null;
            };

            account = new AccountEntity(
                    res.getInt("code"),
                    res.getString("email"),
                    type
            );

            res.close();

        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return account;

    }
}
