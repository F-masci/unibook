package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotOwnedException;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.exceptions.login.SessionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InsertCourseBookControllerTest {

    private static final String VALID_ISBN = "9780201633610";
    private static final String VALID_TITLE = "Design Patterns";

    /**
     * Viene testato il metodo per cercare i dati di un libro che presente è nel catalogo
     * @throws BookException Viene sollevata se il libro non viene trovato
     */
    @Test
    void testGetBookInformationWhitBookInLibrary() throws BookException {
        // Viene istanziato il controller applicativo per eseguire il metodo
        InsertCourseBookController controller = new InsertCourseBookController();
        // Viene istanziato il bean contenente l'ISBN del libro
        BookBean bean = new BookBean(VALID_ISBN);
        // Vengono caricati i dati del libro nel bean
        // Se non vengono sollevate eccezioni il caricamento è stato effettuato correttamente
        controller.getBookInformation(bean);
        // Si controlla che i dati restituiti siano corretti
        Assertions.assertEquals(VALID_TITLE, bean.getName());
    }

    /**
     * Viene testato il metodo per cercare i dati di un libro che non è presente nel catalogo
     * Il metodo dovrebbe restituire un eccezione che ha come causa l'eccezione relativa
     * al libro non trovato
     */
    @Test
    void testGetBookInformationWhitBookNotInLibrary() {
        try {
            // Viene istanziato il controller applicativo per eseguire il metodo
            InsertCourseBookController controller = new InsertCourseBookController();
            // Viene istanziato il bean contenente l'ISBN del libro
            BookBean bean = new BookBean("0000000000000");
            // Vengono caricati i dati del libro nel bean
            // Se non vengono sollevate eccezioni il caricamento è stato effettuato correttamente
            controller.getBookInformation(bean);
            Assertions.fail("Il libro viene trovato correttamente");
        } catch(BookException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per ottenere i dati del libro

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // l'assenza del libro nella catalogo disponibile
            Assertions.assertEquals(BookNotFoundException.class, e.getCause().getClass());
        }
    }

    /**
     * Viene testato il metodo per inserire un libro in un corso
     * con un libro non ancora presente in un corso esistente
     */
    @Test
    void testInsertBookInCourseWithValidInsert() {
        // Viene eseguito il login del professore
        LoginBean loginBean = new LoginBean("professore@uniroma2.eu", "professore");
        LoginController loginController = new LoginController();

        // Viene istanziato il controller applicativo per eseguire il metodo
        InsertCourseBookController controller = new InsertCourseBookController();
        // Viene istanziato il bean contenente il codice del corso
        CourseBean courseBean = new CourseBean(2);
        // Viene istanziato il bean contenente i dati del libro
        BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
        // Viene inserito il libro nel corso
        // Se non vengono sollevate eccezioni l'inserimento è stato effettuato correttamente
        Assertions.assertDoesNotThrow(() -> {
            loginController.login(loginBean);
            controller.insertBookInCourse(courseBean, bookBean);
        });
    }

    /**
     * Viene testato il metodo per inserire un libro in un corso
     * con un libro non ancora presente in un corso non esistente
     */
    @Test
    void testInsertBookInCourseWithCourseNotPresent() {
        try {
            // Viene eseguito il login del professore
            LoginBean loginBean = new LoginBean("professore@uniroma2.eu", "professore");
            LoginController loginController = new LoginController();
            loginController.login(loginBean);

            // Viene istanziato il controller applicativo per eseguire il metodo
            InsertCourseBookController controller = new InsertCourseBookController();
            // Viene istanziato il bean contenente il codice del corso
            CourseBean courseBean = new CourseBean(0);
            // Viene istanziato il bean contenente i dati del libro
            BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
            // Viene inserito il libro nel corso
            // Se non vengono sollevate eccezioni l'inserimento è stato effettuato correttamente
            controller.insertBookInCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene inserito correttamente");
        } catch (CourseException | SessionException | LoginException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per inserire il libro nel corso

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // l'assenza del corso
            Assertions.assertEquals(CourseNotFoundException.class, e.getCause().getClass());
        }
    }

    /**
     * Viene testato il metodo per inserire un libro in un corso
     * con un libro già presente in un corso esistente
     */
    @Test
    void testInsertBookInCourseWithBookAlreadyPresent() {
        try {
            // Viene eseguito il login del professore
            LoginBean loginBean = new LoginBean("professore@uniroma2.eu", "professore");
            LoginController loginController = new LoginController();
            loginController.login(loginBean);

            // Viene istanziato il controller applicativo per eseguire il metodo
            InsertCourseBookController controller = new InsertCourseBookController();
            // Viene istanziato il bean contenente il codice del corso
            CourseBean courseBean = new CourseBean(2);
            // Viene istanziato il bean contenente i dati del libro
            BookBean bookBean = new BookBean("9788891904591", "Applicare UML e i pattern: analisi e progettazione orientata agli oggetti");
            // Viene inserito il libro nel corso
            // Se non vengono sollevate eccezioni l'inserimento è stato effettuato correttamente
            controller.insertBookInCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene inserito correttamente");
        } catch (CourseException | SessionException | LoginException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per inserire il libro nel corso

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // la presenza del libro nel corso da prima dell'inserimento
            Assertions.assertEquals(BookAlreadyInCourseException.class, e.getCause().getClass());
        }
    }

    /**
     * Viene testato il metodo per inserire un libro in un corso
     * di cui il professore non è effettivamente proprietario
     */
    @Test
    void testInsertBookInCourseNotOwnedByProfessor() {
        try {
            // Viene eseguito il login del professore
            LoginBean loginBean = new LoginBean("professore@uniroma2.eu", "professore");
            LoginController loginController = new LoginController();
            loginController.login(loginBean);

            // Viene istanziato il controller applicativo per eseguire il metodo
            InsertCourseBookController controller = new InsertCourseBookController();
            // Viene istanziato il bean contenente il codice del corso
            CourseBean courseBean = new CourseBean(1);
            // Viene istanziato il bean contenente i dati del libro
            BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
            // Viene inserito il libro nel corso
            // Se non vengono sollevate eccezioni l'inserimento è stato effettuato correttamente
            controller.insertBookInCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene inserito correttamente");
        } catch (CourseException | SessionException | LoginException e) {
            // Il metodo solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura per inserire il libro nel corso

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // la presenza del libro nel corso da prima dell'inserimento
            Assertions.assertEquals(CourseNotOwnedException.class, e.getCause().getClass());
        }
    }

}
