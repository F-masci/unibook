package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.exceptions.book.sellable.SellableBookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.facade.ManageSellableBookFacade;
import org.jetbrains.annotations.NotNull;

public class InsertSellableBookCLI extends ManageSellableBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei libri in vendita
    private final ManageSellableBookFacade facade = new ManageSellableBookFacade();

    /**
     * Inserisce il libro in vendita completando il caso d'uso
     * @param bean Deve contenere i dati del libro da inserire e il codice della sessione dell'utente che lo sta inserendo
     * @throws SellableBookException Viene sollevata se il libro non può essere venduto in questo corso
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
    public void insertSellableBook(@NotNull SellableBookBean bean) throws SellableBookException, SessionException, CourseException {
        facade.insertSellableBook(bean);
    }

}
