package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.professor.ManageBookCli;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;

import java.util.List;

public class PageManageBookCLI extends GenericPageCLI {

    private final ManageBookCli controller;

    protected PageManageBookCLI(ManageBookCli controller) {
        this.controller = controller;
    }

    protected ManageBookCli getController() {
        return controller;
    }

    protected void printCoursesList() {
        CoursesListBean bean = new CoursesListBean();
        controller.retriveCoursesBySession(bean);
        List<CourseBean> courses = bean.getList();

        Printer.println("\n--- I TUOI CORSI ---");
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c);
        }
        Printer.println("");
    }

}
