package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.professor.PageBooksListCLI;
import it.ispw.unibook.view.cli.professor.PageCoursesListCLI;

public class HomeCLI {

    public void showCourses() {
        changeView(new PageCoursesListCLI());
    }

    public void showBooks() {
        changeView(new PageBooksListCLI());
    }

    public void addBook() {

    }

    public void deleteBook() {

    }

    private void changeView(PageCLI page) {
        page.init();
    }

}
