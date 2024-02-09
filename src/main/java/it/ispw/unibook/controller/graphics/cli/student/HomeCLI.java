package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.student.*;

public class HomeCLI {

    public void showCourses() { changeView(new PageCoursesListCLI()); }

    public void showBooks() { changeView(new PageBooksListCLI()); }

    public void showActiveSellableBooks() { changeView(new PageActiveNegotiationCLI()); }

    public void showPurchaseBook() { changeView(new PagePurchaseBookCLI()); }

    public void showInsertSellableBook() { changeView(new PageInsertSellableBookCLI()); }

    public void showRemoveSellableBook() { changeView(new PageRemoveSellableBookCLI()); }

    public void showMarkSellableBookSold() { changeView(new PageMarkSellableBookSoldCLI()); }

    private void changeView(PageCLI page) {
        page.display();
    }

}
