package it.ispw.unibook.controller.application;

import it.ispw.unibook.utils.Session;
import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.dao.LoginDao;
import it.ispw.unibook.dao.LoginDaoAppJDBC;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.SessionManager;

public class LoginController {

    private String email;
    private String password;

    public LoginController(LoginBean bean) {
        email = bean.getEmail();
        password = bean.getPassword();
    }

    public void login() throws EmailNotFoundException, IncorrectPasswordException {

        LoginDao dao = new LoginDaoAppJDBC();
        AccountEntity account = dao.login(email, password);
        SessionManager.addSession(account);
        Session.setSessionId(account.getCode());

    }

}
