package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;

import java.io.IOException;
import java.util.List;

public class PageManageBookCLI extends GenericPageCLI {

    protected PageManageBookCLI() {}

    // FIXME exceptions
    protected void printCoursesList(ManageBookCLI controller) throws SessionException {
        CoursesListBean bean = new CoursesListBean();
        controller.retrieveCoursesBySession(bean);
        List<CourseBean> courses = bean.getList();

        Printer.println("\n--- I TUOI CORSI ---");
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c);
        }
        Printer.println("");
    }

    // FIXME exceptions
    protected void printCourseBooksList(ManageBookCLI controller, int courseCode) throws CourseException {
        BooksListBean bean = new BooksListBean(courseCode);
        controller.retrieveBooksByCourse(bean);
        List<BookBean> books = bean.getList();

        Printer.println("\n--- LIBRI COLLEGATI ---");

        for (BookBean b : books) {
            Printer.println("[" + b.getISBN() + "] " + b);
        }

        Printer.println("");

    }

    protected String requestBookCode() throws EscCliException {
        return requestBookCode("ISBN del libro (oppure esc per uscire): ");
    }
    protected String requestBookCode(String msg) throws EscCliException {
        return requestString(msg);
    }

    protected int requestCourseCode() throws EscCliException {
        return requestCourseCode("Codice del corso (oppure esc per uscire): ");
    }
    protected int requestCourseCode(String msg) throws EscCliException {
        return requestInt(msg);
    }

}
