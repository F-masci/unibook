package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

/**
 * Controller applicativo del caso d'uso <i>Rimuovi libro in vendita</i>
 */
public class RemoveSellableBookController {

    /**
     * Rimuove il libro in vendita completando il caso d'uso
     * @param bean Deve contenere il codice del libro in vendita
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void removeSellableBook(@NotNull SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            SellableBookDao sellableBookDao = SellableBookDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il libro in vendita corrispondete al codice fornito
            // Se il libro in vendita non viene trovato viene sollevata l'eccezione
            SellableBookEntity sellableBook = sellableBookDao.retrieveSellableBookByCode(bean.getCode());
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity seller = SessionManager.getAccountBySessionID(bean.getSessionId());
            // Si carica il dao per la comunicazione con la persistenza
            UniversityDao universityDao = UniversityDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il corso a cui è associato il libro in vendita
            // Se il corso non viene trovato viene sollevata l'eccezione
            CourseEntity course = universityDao.retrieveCourseBySellableBook(sellableBook, seller);
            // Il libro in vendita viene rimosso da quelli del corso
            // Il salvataggio in persistenza è gestito dall'entità
            course.removeSellableBook(sellableBook);
        } catch (SellableBookNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch(CourseNotFoundException e) {
            throw new CourseException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
