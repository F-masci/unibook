package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.AccountDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.exceptions.negotiation.BuyerAlreadyInNegotiationException;
import it.ispw.unibook.exceptions.negotiation.BuyerIsSellerException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

/**
 * Controller applicativo del caso d'uso <i>Notifica di voler acquistare un libro</i>
 */
public class PurchaseBookController {

    /**
     * L'utente loggato viene inserito tra gli acquirenti del libro completando il caso d'uso
     * @param bean Deve contenere il libro che l'utente loggato vuole comprare
     * @throws SellableBookException Viene sollevata se il libro non viene trovato o è già stato venduto
     * @throws NegotiationException Viene sollevata se l'acquirente è già tra la lista degli acquirenti o è il venditore del libro
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void purchaseBook(@NotNull SellableBookBean bean) throws SellableBookException, NegotiationException, SessionException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
            // Viene cercato sulla persistenza il libro in vendita corrispondete al codice fornito
            // Se il libro in vendita non viene trovato viene sollevata l'eccezione
            SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(bean.getCode());
            // Controlla che il libro non sia già stato venduto
            if(sellableBook.isSold()) throw new SellableBookAlreadySoldException();
            // Si controlla che l'account che ha inviato il messaggio non sia il venditore del libro
            AccountEntity loggedUser = SessionManager.getAccountBySessionID(bean.getSessionId());
            if(sellableBook.getSeller().equals(loggedUser)) throw new BuyerIsSellerException();
            // Viene istanziato il DAO tramite factory per cercare l'acquirente
            AccountDao accountDao = ApplicationDaoFactory.getInstance().getAccountDao();
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity buyer = SessionManager.getAccountBySessionID(bean.getSessionId());
            // L'account viene aggiunto tra gli acquirenti
            // Il salvataggio in persistenza è gestito dall'entità
            sellableBook.addBuyer(buyer);
        } catch (SellableBookNotFoundException | SellableBookAlreadySoldException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch (BuyerAlreadyInNegotiationException | BuyerIsSellerException e) {
            throw new NegotiationException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
