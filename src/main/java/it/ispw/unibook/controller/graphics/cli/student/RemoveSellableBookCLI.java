package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import org.jetbrains.annotations.NotNull;

public class RemoveSellableBookCLI extends ManageSellableBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    /**
     * Rimuove il libro in vendita completando il caso d'uso
     * @param bean Deve contenere il codice del libro in vendita
     * @throws SellableBookException Viene sollevata se il libro in vendita non è stato trovato
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void removeSellableBook(@NotNull SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        facade.removeSellableBook(bean);
    }

}
