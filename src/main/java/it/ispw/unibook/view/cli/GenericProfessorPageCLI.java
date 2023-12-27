package it.ispw.unibook.view.cli;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.graphics.cli.professor.GenericControllerCLI;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class GenericProfessorPageCLI extends GenericPageCLI {

    protected void printCoursesList() {
        CoursesListBean bean = new CoursesListBean();
        GenericControllerCLI controller = new GenericControllerCLI();
        controller.retriveCourseBySession(bean);
        List<CourseBean> courses = bean.getList();

        Printer.println("Questi sono i tuoi corsi");
        for(CourseBean c: courses) {
            Printer.println("[" + c.getCode() + "] " + c.getName() + " - " + c.getStartYear() + "/" + c.getEndYear());
        }
    }

}
