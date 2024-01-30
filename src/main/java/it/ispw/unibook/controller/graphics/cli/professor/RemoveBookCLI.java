package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.controller.graphics.cli.ManageBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.facade.ManageCourseBookFacade;
import org.jetbrains.annotations.NotNull;

public class RemoveBookCLI extends ManageBookCLI {

    // Facade per l'accesso al sottosistema di gestione dei corsi e dei libri
    private final ManageCourseBookFacade facade = new ManageCourseBookFacade();

    /**
     * Rimuove il libro dal corso svolgendo il caso d'uso
     * @param courseBean Deve contenere il codice del corso da cui rimuovere il libro
     * @param bookBean Deve contenere tutti i dati del libro da inserire
     * @throws BookException Viene sollevata se il libro non è presente nel corso
     */
    public void removeBookFromCourse(@NotNull CourseBean courseBean, @NotNull BookBean bookBean) throws BookException, CourseException {
        facade.removeBookFromCourse(courseBean, bookBean);
    }

}
