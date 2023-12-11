package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.professor.CoursesListCLI;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.GenericPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;
import java.util.List;

public class PageCoursesListCLI extends GenericPageCLI implements PageCLI {

    private final CoursesListCLI controller = new CoursesListCLI();

    @Override
    public void init() {

        Printer.clear();

        Printer.println("--- CORSI ---");

        showCourses();
        waitForExit();

    }

    private void showCourses() {
        CoursesListBean bean = new CoursesListBean();
        controller.getCourses(bean);
        List<CourseBean> courses = bean.getList();

        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c.getName() + " - " + c.getStartYear() + "/" + c.getEndYear());
        }

    }

    private void waitForExit() {
        Printer.println("Premi un qualsisi tasto per tornare alla home");
        try {
            br.readLine();
        } catch (IOException e) {
            Printer.error("Errore durante la lettura dell'input");
            System.exit(-1);
        }
    }

}
