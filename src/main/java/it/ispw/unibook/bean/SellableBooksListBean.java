package it.ispw.unibook.bean;

import java.util.List;

public class SellableBooksListBean extends Bean {

    private List<SellableBookBean> sellableBooks;

    public void setList(List<SellableBookBean> sellableBooks) {
        this.sellableBooks = sellableBooks;
    }
    public List<SellableBookBean> getList() {
        return sellableBooks;
    }

}
