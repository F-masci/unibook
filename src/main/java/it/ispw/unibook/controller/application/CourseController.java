package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.CoursesListBean;
import it.ispw.unibook.dao.CourseDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.CourseDaoFactory;
import it.ispw.unibook.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller applicativo per l'implementazione delle operazioni comuni
 * sulle entità Corso richieste ai vari controller
 */
public class CourseController {

    /**
     * Carica nel bean la lista dei corsi associati al profilo che ha effettuato la richiesta
     * @param bean Deve contenere il codice della sessione corrente. Contiene la lista dei corsi collegati
     * @throws SessionException Viene sollevata in caso in cui il codice della sessione non sia valido
     */
    public void retrieveCoursesBySession(CoursesListBean bean) throws SessionException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            CourseDao dao = CourseDaoFactory.getInstance().getDao();
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity account = SessionManager.getAccountBySessionID(bean.getSessionId());

            // Viene usato il dao per ottenere dallo stato di persistenza tutti i corsi associati
            // all'account che ha inviato il messaggio
            List<CourseEntity> courses = dao.retrieveCoursesByProfessor(account);
            // Si carica la lista dei corsi all'interno del bean
            insertCoursesListIntoBean(courses, bean);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

    /**
     * Carica nel bean la lista dei corsi presenti nel sistema
     * @param bean Contiene la lista dei corsi collegati
     */
    public void retrieveCourses(CoursesListBean bean) {
        // Si carica il dao per la comunicazione con la persistenza
        CourseDao dao = CourseDaoFactory.getInstance().getDao();
        // Viene usato il dao per ottenere dallo stato di persistenza tutti i corsi
        List<CourseEntity> courses = dao.retrieveCourses();
        // Si carica la lista dei corsi all'interno del bean
        insertCoursesListIntoBean(courses, bean);
    }

    /**
     * Funzione ausiliare per formattare la lista da inserire nel bean
     * a partire dalla lista di entità
     * @param courses Lista di entità da cui creare la lista per il bean
     * @param bean Bean su cui caricare la lista
     */
    private void insertCoursesListIntoBean(List<CourseEntity> courses, CoursesListBean bean) {
        // Si prepara la lista da restituire al chiamante
        List<CourseBean> list = new ArrayList<>();
        for(CourseEntity c: courses) {
            // Viene creato il bean del libro a partire dall'entità
            CourseBean course = createCourseBeanFromEntity(c);
            // Si aggiunge il bean alla lista
            list.add(course);
        }
        // Viene impostata la lista nel bean
        bean.setList(list);
    }

    /**
     * Funzione ausiliare per formattare il bean a partire dall'entità
     * @param course Entità da cui creare il bean
     * @return Bean contenente i dati dell'entità
     */
    private CourseBean createCourseBeanFromEntity(CourseEntity course) {
        return new CourseBean(
            course.getCode(),
            course.getName(),
            course.getStartYear(),
            course.getEndYear()
        );
    }

}
