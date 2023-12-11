package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.Bean;
import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.dao.LoginDao;
import it.ispw.unibook.dao.LoginDaoAppJDBC;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.SessionManager;

public class LoginController {

    public void login(LoginBean bean) throws EmailNotFoundException, IncorrectPasswordException {

        LoginDao dao = new LoginDaoAppJDBC();
        AccountEntity account = dao.login(bean.getEmail(), bean.getPassword());
        SessionManager.addSession(account);
        Bean.setSessionId(account.getCode());

    }

}
