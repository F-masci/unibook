package it.ispw.unibook.controller.applicativi;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.grafici.cli.Printer;
import it.ispw.unibook.dao.LoginDao;
import it.ispw.unibook.dao.LoginDaoJDBC;

import java.sql.SQLException;

public class LoginController {

    private LoginBean bean;

    public LoginController(LoginBean bean) {
        this.bean = bean;
        System.out.println("Login di " + bean.getEmail() + ": " + bean.getPassword());
    }

    public void login() {

        LoginDao dao = new LoginDaoJDBC();
        try {
            dao.login(bean.getEmail(), bean.getPassword());
        } catch(SQLException e) {
            Printer.error(e);
        }

    }

}
