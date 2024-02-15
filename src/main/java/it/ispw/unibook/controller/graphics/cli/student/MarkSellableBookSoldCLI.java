package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import org.jetbrains.annotations.NotNull;

public class MarkSellableBookSoldCLI extends ManageSellableBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    /**
     * Segna il libro come venduto completando il caso d'uso
     * @param sellableBookBean Deve contenere il codice del libro in vendita
     * @param buyerBean Deve contenere il codice dell'account dell'acquirente
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato o è stato già venduto
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void markSellableBookSold(@NotNull SellableBookBean sellableBookBean, @NotNull AccountBean buyerBean) throws SellableBookException, AccountNotFoundException, SessionException {
        facade.markSellableBookSold(sellableBookBean, buyerBean);
    }

}
