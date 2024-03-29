package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotOwnedException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Controller applicativo del caso d'uso <i>Rimuovi libro in vendita</i>
 */
public class RemoveCourseBookController {

    /**
     * Rimuove il libro dal corso completando il caso d'uso
     * @param courseBean Deve contenere il codice del corso in cui inserire il libro
     * @param bookBean Deve contenere il codice del libro da rimuovere
     * @throws CourseException Viene sollevata se il corso non è stato trovato, il libro non è presente nel corso o il professore non è il proprietario del corso
     */
    public void removeBookFromCourse(@NotNull CourseBean courseBean, @NotNull BookBean bookBean) throws CourseException, SessionException {
        try {
            // Viene istanziata l'entità del libro da rimuovere
            BookEntity book = new BookEntity(bookBean.getISBN());
            // Viene istanziato il DAO tramite factory per cercare il corso
            UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il corso corrispondete al codice fornito
            // Se il corso non viene trovato viene sollevata l'eccezione
            CourseEntity course = dao.retrieveCourseByCode(courseBean.getCode());
            // Viene controllato che il professore sia effettivamente il proprietario del corso
            AccountEntity loggedUser = SessionManager.getAccountBySessionID(courseBean.getSessionId());
            List<CourseEntity> sessionCourses = dao.retrieveCoursesByProfessor(loggedUser);
            if(!sessionCourses.contains(course)) throw new CourseNotOwnedException();
            // Viene rimosso il libro al corso
            // Il salvataggio in persistenza è gestito dall'entità
            course.removeBook(book);
        }catch (CourseNotFoundException | CourseNotOwnedException | BookNotInCourseException e) {
            throw new CourseException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
