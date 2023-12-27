package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.professor.PageBooksListCLI;
import it.ispw.unibook.view.cli.professor.PageInsertBookCLI;
import it.ispw.unibook.view.cli.professor.PageRemoveBookCLI;

public class HomeCLI {

    public void showBooks() {
        changeView(new PageBooksListCLI());
    }

    public void addBook() {
        changeView(new PageInsertBookCLI());
    }

    public void deleteBook() {
        changeView(new PageRemoveBookCLI());
    }

    private void changeView(PageCLI page) {
        page.display();
    }

}
