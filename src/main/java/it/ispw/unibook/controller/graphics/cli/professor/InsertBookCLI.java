package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import org.jetbrains.annotations.NotNull;

public class InsertBookCLI extends ManageBookCLI {

    // Il controller grafico eredita da quello generale l'accesso tramite facade
    // al sottosistema di gestione dei corsi e dei libri associati

    /**
     * Cerca il libro nella libreria tramite ISBN
     * @param bean Deve contenere l'ISBN del libro da cercare.
     *             Contiene il dati del libro trovato
     * @throws BookException Viene sollevata se il libro non è stato trovato
     */
     public void getBookInformation(@NotNull BookBean bean) throws BookException {
         facade.getBookInformation(bean);
     }

    /**
     * Inserisce il libro al corso svolgendo il caso d'uso
     * @param courseBean Deve contenere il codice del corso in cui inserire il libro
     * @param bookBean Deve contenere tutti i dati del libro da inserire
     * @throws CourseException Viene sollevata se il corso non è stato trovato
     */
     public void insertBookInCourse(@NotNull CourseBean courseBean, @NotNull BookBean bookBean) throws CourseException {
         facade.insertBookInCourse(courseBean, bookBean);
     }

}
