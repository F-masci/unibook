package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.dao.LibraryDao;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotOwnedException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.LibraryDaoFactory;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Controller applicativo del caso d'uso <i>Inserisci libro in un corso</i>
 */
public class InsertCourseBookController {

    /**
     * Cerca il libro nella libreria tramite ISBN
     * @param bean Deve contenere l'ISBN del libro da cercare.
     *             Contiene il dati del libro trovato
     * @throws BookException Viene sollevata se il libro non è stato trovato
     */
    public void getBookInformation(@NotNull BookBean bean) throws BookException {
        try {
            // Viene istanziato il DAO tramite factory per cercare il libro
            LibraryDao dao = LibraryDaoFactory.getInstance().getDao();
            // Viene cercato il libro tramite il DAO
            BookEntity book = dao.searchBookByISBN(bean.getISBN());
            // Vengono impostati sul bean i dati del libro trovato da restituire
            bean.setName(book.getTitle());
        } catch (BookNotFoundException e) {
            throw new BookException(e.getMessage(), e);
        }
    }

    /**
     * Inserisce il libro al corso completando il caso d'uso
     * @param courseBean Deve contenere il codice del corso in cui inserire il libro
     * @param bookBean Deve contenere tutti i dati del libro da inserire
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void insertBookInCourse(@NotNull CourseBean courseBean, @NotNull BookBean bookBean) throws CourseException, SessionException {
        try {
            // Viene istanziata l'entità del libro da inserire
            BookEntity book = new BookEntity(bookBean.getISBN(), bookBean.getName());
            // Viene istanziato il DAO tramite factory per cercare il corso
            UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il corso corrispondete al codice fornito
            // Se il corso non viene trovato viene sollevata l'eccezione
            CourseEntity course = dao.retrieveCourseByCode(courseBean.getCode());
            // Viene controllato che il professore sia effettivamente il proprietario del corso
            AccountEntity loggedUser = SessionManager.getAccountBySessionID(courseBean.getSessionId());
            List<CourseEntity> sessionCourses = dao.retrieveCoursesByProfessor(loggedUser);
            if(!sessionCourses.contains(course)) throw new CourseNotOwnedException();
            // Viene aggiungo il libro al corso
            // Il salvataggio in persistenza è gestito dall'entità
            course.addBook(book);
        } catch(BookAlreadyInCourseException | CourseNotFoundException | CourseNotOwnedException e) {
            throw new CourseException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
