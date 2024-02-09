package it.ispw.unibook.controller.graphics.gui.student;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.graphics.gui.GenericGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import it.ispw.unibook.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.jetbrains.annotations.NotNull;

public abstract class ManageSellableBookGUI extends GenericGUI {

    @FXML
    protected void returnToHomePage() {
        changePage(PagesGUI.HOME_STUDENT);
    }
    private final ManageSellableBookFacade manageSellableBookFacade = new ManageSellableBookFacade();

    protected void loadAllCourses(ComboBox<String> combo) {
        CoursesListBean bean = new CoursesListBean();
        retrieveCourses(bean);
        loadCoursesComboBox(combo, bean);
    }

    protected void loadCourseBooks(ComboBox<String> combo, int course) throws CourseException {
        BooksListBean bean = new BooksListBean(course);
        retrieveBooksByCourse(bean);
        loadCourseBooksComboBox(combo, bean);
    }

    protected void loadCourseSellableBooks(ComboBox<String> combo, int course) throws CourseException {
        CourseBean courseBean = new CourseBean(course);
        SellableBooksListBean sellableBooksBean =  retrieveSellableBooksByCourse(courseBean);
        loadSellableBooksComboBox(combo, sellableBooksBean);
    }

    protected void loadIsbnSellableBooks(ComboBox<String> combo, String isbn) throws FieldNotValidException {
        BookBean bookBean = new BookBean(isbn);
        SellableBooksListBean sellableBooksBean = retrieveSellableBooksByIsbn(bookBean);
        loadSellableBooksComboBox(combo, sellableBooksBean);
    }

    protected void loadSessionSellableBooks(ComboBox<String> combo) {
        try {
            SellableBooksListBean bean = new SellableBooksListBean();
            retrieveSellableBooksBySession(bean);
            loadSellableBooksComboBox(combo, bean);
        } catch (SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    protected void loadSellableBookBuyers(ComboBox<String> combo, int sellableBook) throws SellableBookException {
        SellableBookBean sellableBookBean = new SellableBookBean(sellableBook);
        AccountsListBean accountsBean = retrieveActiveNegotiationBySellableBook(sellableBookBean);
        loadAccountsComboBox(combo, accountsBean);
    }


    protected void retrieveCourses(CoursesListBean bean) {
        manageSellableBookFacade.retrieveCourses(bean);
    }
    protected void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        manageSellableBookFacade.retrieveBooksByCourse(bean);
    }
    protected void retrieveSellableBooksBySession(SellableBooksListBean bean) throws SessionException {
        manageSellableBookFacade.retrieveSellableBooksBySession(bean);
    }
    protected SellableBooksListBean retrieveSellableBooksByIsbn(BookBean bean) {
        return manageSellableBookFacade.retrieveSellableBooksByIsbn(bean);
    }
    protected SellableBooksListBean retrieveSellableBooksByCourse(CourseBean bean) throws CourseException {
        return manageSellableBookFacade.retrieveSellableBooksByCourse(bean);
    }
    public void retrieveSellableBooksByActiveNegotiationOfSession(@NotNull SellableBooksListBean bean) throws SessionException {
        manageSellableBookFacade.retrieveSellableBooksByActiveNegotiationOfSession(bean);
    }
    protected AccountsListBean retrieveActiveNegotiationBySellableBook(SellableBookBean bean) throws SellableBookException {
        return manageSellableBookFacade.retrieveActiveNegotiationBySellableBook(bean);
    }

}
