package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.negotiation.NegotiationException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import org.jetbrains.annotations.NotNull;

public class PurchaseBookCLI extends ManageSellableBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    /**
     * L'utente loggato viene inserito tra gli acquirenti del libro completando il caso d'uso
     * @param bean Deve contenere il libro che l'utente loggato vuole comprare
     * @throws SellableBookException Viene sollevata se il libro non viene trovato o è già stato venduto
     * @throws NegotiationException Viene sollevata se l'acquirente è già tra la lista degli acquirenti o è il venditore del libro
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void purchaseBook(@NotNull SellableBookBean bean) throws SellableBookException, NegotiationException, SessionException {
        facade.purchaseBook(bean);
    }

}
