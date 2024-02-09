package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.course.BookNotInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoveCourseBookControllerTest {

    private static final String VALID_ISBN = "9780201633610";
    private static final String VALID_TITLE = "Design Patterns";

    /**
     * Viene testato il metodo per rimuovere un libro da un corso
     * con un libro presente in un corso esistente
     */
    @Test
    void testRemoveBookFromCourseWithValivRemove() {
        // Viene istanziato il controller applicativo per eseguire il metodo
        RemoveCourseBookController controller = new RemoveCourseBookController();
        // Viene istanziato il bean contenente il codice del corso
        CourseBean courseBean = new CourseBean(1);
        // Viene istanziato il bean contenente i dati del libro
        BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
        // Viene rimosso il libro dal corso
        // Se non vengono sollevate eccezioni la rimozione è stata effettuata correttamente
        Assertions.assertDoesNotThrow(() -> controller.removeBookFromCourse(courseBean, bookBean));
    }

    /**
     * Viene testato il metodo per rimuovere un libro da un corso
     * con un libro in un corso non esistente
     * @throws BookException Viene sollevata se il libro non è presente all'interno del corso
     */
    @Test
    void testRemoveBookFromCourseWithCourseNotPresent() throws BookException {
        try {
            // Viene istanziato il controller applicativo per eseguire il metodo
            RemoveCourseBookController controller = new RemoveCourseBookController();
            // Viene istanziato il bean contenente il codice del corso
            CourseBean courseBean = new CourseBean(0);
            // Viene istanziato il bean contenente i dati del libro
            BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
            // Viene rimosso il libro dal corso
            // Se non vengono sollevate eccezioni la rimozione è stata effettuata correttamente
            controller.removeBookFromCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene rimosso correttamente");
        } catch(CourseException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per rimuovere il libro dal corso

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // l'assenza del corso
            Assertions.assertEquals(CourseNotFoundException.class, e.getCause().getClass());
        }
    }

    /**
     * Viene testato il metodo per rimuovere un libro da un corso
     * con un libro non presente in un corso esistente
     * @throws CourseException Viene sollevata se il libro non è presente all'interno del corso
     */
    @Test
    void testRemoveBookFromCourseWithBookNotInCourse() throws CourseException {
        try {
            // Viene istanziato il controller applicativo per eseguire il metodo
            RemoveCourseBookController controller = new RemoveCourseBookController();
            // Viene istanziato il bean contenente il codice del corso
            CourseBean courseBean = new CourseBean(1);
            // Viene istanziato il bean contenente i dati del libro
            BookBean bookBean = new BookBean("0000000000000");
            // Viene rimosso il libro dal corso
            // Se non vengono sollevate eccezioni la rimozione è stata effettuata correttamente
            controller.removeBookFromCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene rimosso correttamente");
        } catch (BookException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per rimuovere il libro dal corso

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // l'assenza del libro nel corso
            Assertions.assertEquals(BookNotInCourseException.class, e.getCause().getClass());
        }
    }

}
