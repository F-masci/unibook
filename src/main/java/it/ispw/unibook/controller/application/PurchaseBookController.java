package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.exceptions.negotiation.BuyerAlreadyInNegotiationException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.factory.SellableBookDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

/**
 * Controller applicativo del caso d'uso <i>Acquista libro</i>
 */
public class PurchaseBookController {

    /**
     * L'utente loggato viene inserito tra gli acquirenti del libro completando il caso d'uso
     * @param bean Deve contenere il libro che l'utente loggato vuole comprare
     * @throws SellableBookException Viene sollevata se il libro non viene trovato
     * @throws NegotiationException Viene sollevata se l'acquirente è già tra la lista degli acquirenti
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void purchaseBook(@NotNull SellableBookBean bean) throws SellableBookException, NegotiationException, SessionException {
        try {
            // Si carica il dao per la comunicazione con la persistenza
            SellableBookDao dao = SellableBookDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il libro in vendita corrispondete al codice fornito
            // Se il libro in vendita non viene trovato viene sollevata l'eccezione
            SellableBookEntity sellableBook = dao.retrieveSellableBookByCode(bean.getCode());
            // Si cerca l'account collegato alla sessione che ha inviato il messaggio
            AccountEntity buyer = SessionManager.getAccountBySessionID(bean.getSessionId());
            // L'account viene aggiunto tra gli acquirenti
            // Il salvataggio in persistenza è gestito dall'entità
            sellableBook.addBuyer(buyer);
        } catch (SellableBookNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
        } catch (BuyerAlreadyInNegotiationException e) {
            throw new NegotiationException(e.getMessage(), e);
        } catch (SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
