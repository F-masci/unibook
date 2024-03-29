package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookControllerTest {

    /**
     * Viene testato il metodo per ottenere la lista dei libri di un corso esistente
     */
    @Test
    void testRetrieveBooksByCourseWithCoursePresent() {
        // Viene istanziato il controller applicativo per eseguire il metodo
        BookController controller = new BookController();
        // Viene istanziato il bean contenente il codice del corso
        BooksListBean bean = new BooksListBean(1);
        // Viene caricata la lista dei libri nel bean
        // Se non vengono sollevate eccezioni il caricamento è stato effettuato correttamente
        Assertions.assertDoesNotThrow(() -> controller.retrieveBooksByCourse(bean));
    }

    /**
     * Viene testato il metodo per ottenere la lista dei libri di un corso non esistente
     * Il metodo dovrebbe restituire un eccezione che ha come causa l'eccezione relativa
     * al corso non trovato
     */
    @Test
    void testRetrieveBooksByCourseWithCourseNotPresent()  {
        try {
            // Viene istanziato il controller applicativo per eseguire il metodo
            BookController controller = new BookController();
            // Viene istanziato il bean contenente il codice del corso
            BooksListBean bean = new BooksListBean(0);
            // Viene caricata la lista dei libri nel bean
            // Se non vengono sollevate eccezioni il caricamento è stato effettuato correttamente
            controller.retrieveBooksByCourse(bean);
            Assertions.fail("I corsi vengono caricati correttamente");
        } catch (CourseException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per ottenere i libri del corso

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // l'assenza del corso
            Assertions.assertEquals(CourseNotFoundException.class, e.getCause().getClass());
        }
    }

}
