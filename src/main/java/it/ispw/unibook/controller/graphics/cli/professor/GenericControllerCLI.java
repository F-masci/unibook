package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.controller.application.CourseController;

/**
 * Questo controller grafico implementa tutte quelle funzioni che permettono alle View di ottenere i dati
 * da mostrare all'utente che sono richiesti da più classi. I Controller grafici che gestiscono queste View
 * dovrebbero essere implementati come sottoclasse di questo Controller generale.
 */
public class GenericControllerCLI {

    public void retriveCourseBySession(CoursesListBean bean) {
        CourseController controller = new CourseController();
        controller.retriveCourseBySession(bean);
    }

}
