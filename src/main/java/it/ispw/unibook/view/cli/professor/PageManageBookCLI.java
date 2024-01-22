package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.professor.ManageBookCli;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;

import java.util.List;

public class PageManageBookCLI extends GenericPageCLI {

    protected PageManageBookCLI() {}

    protected void printCoursesList(ManageBookCli controller) {
        CoursesListBean bean = new CoursesListBean();
        controller.retrieveCoursesBySession(bean);
        List<CourseBean> courses = bean.getList();

        Printer.println("\n--- I TUOI CORSI ---");
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c);
        }
        Printer.println("");
    }

    protected void printCourseBooksList(ManageBookCli controller, int courseCode) {
        BooksListBean bean = new BooksListBean(courseCode);
        controller.retrieveBooksByCourse(bean);
        List<BookBean> books = bean.getList();

        Printer.println("--- LIBRI COLLEGATI ---");

        for (BookBean b : books) {
            Printer.println("[" + b.getISBN() + "] " + b);
        }

        Printer.println("");

    }

}
