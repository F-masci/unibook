package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.AccountBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.dao.AccountDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Controller applicativo del caso d'uso <i>Segna libro come venduto</i>
 */
public class MarkSellableBookSoldController {

    /**
     * Segna il libro come venduto completando il caso d'uso
     * @param sellableBookBean Deve contenere il codice del libro in vendita
     * @param buyerBean Deve contenere il codice dell'account dell'acquirente
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato o è stato già venduto
     */
    public void markSellableBookSold(@NotNull SellableBookBean sellableBookBean, @NotNull AccountBean buyerBean) throws SellableBookException, AccountNotFoundException {
        try {
            // Viene istanziato il DAO tramite factory per cercare il libro in vendita
            SellableBookDao sellableBookDao = ApplicationDaoFactory.getInstance().getSellableBookDao();
            // Viene cercato sulla persistenza il libro in vendita corrispondete al codice fornito
            // Se il libro in vendita non viene trovato viene sollevata l'eccezione
            SellableBookEntity sellableBook = sellableBookDao.retrieveSellableBookByCode(sellableBookBean.getCode());
            // Viene istanziato il DAO tramite factory per cercare l'acquirente
            AccountDao accountDao = ApplicationDaoFactory.getInstance().getAccountDao();
            // Viene cercato sulla persistenza l'account dell'acquirente corrispondete al codice fornito
            // Se l'account non viene trovato viene sollevata l'eccezione
            AccountEntity buyer = accountDao.retrieveAccountByCode(buyerBean.getCode());
            // Il libro viene impostato come venduto all'acquirente fornito
            // Il salvataggio in persistenza è gestito dall'entità
            sellableBook.markAsSold(buyer);
        } catch(SellableBookAlreadySoldException | SellableBookNotFoundException e) {
            throw new SellableBookException(e.getMessage(), e);
        }
    }

}
