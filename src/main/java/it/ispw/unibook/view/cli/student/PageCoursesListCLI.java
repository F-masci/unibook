package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.student.GenericStudentCLI;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.util.List;

public class PageCoursesListCLI extends GenericStudentPageCLI implements PageCLI {

    GenericStudentCLI controller = new GenericStudentCLI();

    @Override
    public void display() {
        Printer.clear();
        Printer.println("\n--- PAGINA CORSI ---");

        printCoursesList(controller);

        waitForExit();
    }

}
