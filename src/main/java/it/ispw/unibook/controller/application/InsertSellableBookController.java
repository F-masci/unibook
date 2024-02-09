package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.UnsellableBookInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

/**
 * Controller applicativo del caso d'uso <i>Inserisci libro in vendita</i>
 */
public class InsertSellableBookController {

    /**
     * Inserisce il libro in vendita completando il caso d'uso
     * @param bean Deve contenere i dati del libro da inserire e il codice della sessione dell'utente che lo sta inserendo
     * @throws SellableBookException Viene sollevata se il libro non può essere venduto in questo corso
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void insertSellableBook(@NotNull SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        try {
            // Viene istanziata l'entità dell'utente loggato
            AccountEntity seller = SessionManager.getAccountBySessionID(bean.getSessionId());
            // Viene istanziato il DAO tramite factory per cercare il corso
            UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il corso corrispondete al codice fornito
            // Se il corso non viene trovato viene sollevata l'eccezione
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourse());
            // Viene istanziata l'entità del libro in vendita da inserire
            SellableBookEntity sellableBook = new SellableBookEntity(bean.getISBN(), bean.getPrice(), seller);
            // Viene aggiungo il libro in vendita al corso
            // Il salvataggio in persistenza è gestito dall'entità
            course.addSellableBook(sellableBook);
        } catch (UnsellableBookInCourseException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        } catch (CourseNotFoundException e) {
            throw new CourseException(e.getMessage(), e);
        }
    }

}
