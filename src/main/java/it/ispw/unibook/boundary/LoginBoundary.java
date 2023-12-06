package it.ispw.unibook.boundary;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.controller.applicativi.LoginController;

public class LoginBoundary {

    private LoginBean bean;

    public LoginBoundary(LoginBean bean) {
        this.bean = bean;
    }

    public void login() {
        LoginController controller = new LoginController(bean);
        controller.login();
    }

}
