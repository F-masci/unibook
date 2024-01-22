package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.student.PageBooksListCLI;
import it.ispw.unibook.view.cli.student.PageCoursesListCLI;

public class HomeCLI {

    public void showCourses() { changeView(new PageCoursesListCLI()); }

    public void showBooks() { changeView(new PageBooksListCLI()); }

    private void changeView(PageCLI page) {
        page.display();
    }

}
