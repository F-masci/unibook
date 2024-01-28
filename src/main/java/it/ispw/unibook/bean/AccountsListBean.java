package it.ispw.unibook.bean;

import java.util.List;

public class AccountsListBean extends Bean {

    private List<AccountBean> accounts;

    public void setList(List<AccountBean> accounts) {
        this.accounts = accounts;
    }
    public List<AccountBean> getList() {
        return accounts;
    }
}
