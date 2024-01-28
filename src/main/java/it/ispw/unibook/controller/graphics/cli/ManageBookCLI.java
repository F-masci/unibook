package it.ispw.unibook.controller.graphics.cli;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.ManageCourseBookController;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;

/**
 * Questo controller grafico implementa tutte quelle funzioni che permettono alle View di ottenere i dati
 * da mostrare all'utente che sono richiesti da più classi. I Controller grafici che gestiscono queste View
 * dovrebbero essere implementati come sottoclasse di questo Controller generale.
 */
public class ManageBookCLI {

    private final ManageCourseBookController controller = new ManageCourseBookController();

    public void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        controller.retrieveCoursesBySession(bean);
    }
    public void retrieveCourses(CoursesListBean bean) {
        controller.retrieveCourses(bean);
    }

    public void retrieveBooksByCourse(BooksListBean bean) throws CourseException {
        controller.retrieveBooksByCourse(bean);
    }

}
