package it.ispw.unibook.controller.graphics.gui;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;

import java.util.List;

public abstract class GenericGUI {

    private final ObservableMap<String, Integer> coursesCombo = FXCollections.observableHashMap();
    private final ObservableMap<String, String> booksCombo = FXCollections.observableHashMap();
    private final ObservableMap<String, Integer> sellableBooksCombo = FXCollections.observableHashMap();
    private final ObservableMap<String, Integer> accountsCombo = FXCollections.observableHashMap();

    protected void loadCoursesComboBox(ComboBox<String> combo, CoursesListBean bean) {
        List<CourseBean> course = bean.getList();
        coursesCombo.clear();
        for (CourseBean c : course) {
            this.coursesCombo.put(c.toString(), c.getCode());
        }
        combo.setItems(FXCollections.observableArrayList(this.coursesCombo.keySet()));
    }

    protected int getCourseSelectedFromComboBox(ComboBox<String> combo) throws ComboSelectionNotValidException {
        String value = combo.getValue();
        if (value != null) {
            return coursesCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }


    protected void loadCourseBooksComboBox(ComboBox<String> combo, BooksListBean bean) {
        List<BookBean> books = bean.getList();
        booksCombo.clear();
        for(BookBean b: books) {
            this.booksCombo.put(b.getISBN() + " - " + b.toString(), b.getISBN());
        }
        combo.setItems(FXCollections.observableArrayList(booksCombo.keySet()));
    }

    protected String getCourseBookSelectedFromComboBox(ComboBox<String> combo) throws ComboSelectionNotValidException {
        String value = combo.getValue();
        if (value != null) {
            return booksCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }


    protected void loadSellableBooksComboBox(ComboBox<String> combo, SellableBooksListBean bean) {
        List<SellableBookBean> sellableBooks = bean.getList();
        sellableBooksCombo.clear();
        for (SellableBookBean s : sellableBooks) {
            this.sellableBooksCombo.put(s.toString(), s.getCode());
        }
        combo.setItems(FXCollections.observableArrayList(this.sellableBooksCombo.keySet()));
    }

    protected int getSellableBookSelectedFromComboBox(ComboBox<String> combo) throws ComboSelectionNotValidException {
        String value = combo.getValue();
        if (value != null) {
            return sellableBooksCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }


    protected void loadAccountsComboBox(ComboBox<String> combo, AccountsListBean bean) {
        List<AccountBean> accounts = bean.getList();
        accountsCombo.clear();
        for (AccountBean a : accounts) {
            this.accountsCombo.put(a.toString(), a.getCode());
        }
        combo.setItems(FXCollections.observableArrayList(this.accountsCombo.keySet()));
    }

    protected int getAccountSelectedFromComboBox(ComboBox<String> combo) throws ComboSelectionNotValidException {
        String value = combo.getValue();
        if (value != null) {
            return accountsCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }

    protected void changePage(PagesGUI page) {
        ControllerGUI.setPage(page);
    }

}
