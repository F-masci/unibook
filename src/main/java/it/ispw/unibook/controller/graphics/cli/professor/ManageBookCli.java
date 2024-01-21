package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.CourseController;

/**
 * Questo controller grafico implementa tutte quelle funzioni che permettono alle View di ottenere i dati
 * da mostrare all'utente che sono richiesti da più classi. I Controller grafici che gestiscono queste View
 * dovrebbero essere implementati come sottoclasse di questo Controller generale.
 */
public class ManageBookCli {

    private final CourseController _courseController = new CourseController();

    protected ManageBookCli() {}

    public void retriveCoursesBySession(CoursesListBean bean) {
        _courseController.retriveCourseBySession(bean);
    }

}
