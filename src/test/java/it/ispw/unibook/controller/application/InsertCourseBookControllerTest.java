package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.CourseBean;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.course.BookAlreadyInCourseException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InsertCourseBookControllerTest {

    private static final String VALID_ISBN = "9780201633610";
    private static final String VALID_TITLE = "Design Patterns";

    @Test
    void testGetBookInformationWhitBookInLibrary() throws BookException {
        InsertCourseBookController controller = new InsertCourseBookController();
        BookBean bean = new BookBean(VALID_ISBN);
        controller.getBookInformation(bean);
        Assertions.assertEquals(VALID_TITLE, bean.getName());
    }

    @Test
    void testGetBookInformationWhitBookNotInLibrary() {
        try {
            InsertCourseBookController controller = new InsertCourseBookController();
            BookBean bean = new BookBean("0000000000000");
            controller.getBookInformation(bean);
            Assertions.fail("Il libro viene trovato correttamente");
        } catch(BookException e) {
            Assertions.assertEquals(BookNotFoundException.class, e.getCause().getClass());
        }
    }

    @Test
    void testInsertBookInCourseWithValidInsert() throws CourseException, BookException {
        InsertCourseBookController controller = new InsertCourseBookController();
        CourseBean courseBean = new CourseBean(1);
        BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
        Assertions.assertDoesNotThrow(() -> controller.insertBookInCourse(courseBean, bookBean));
    }

    @Test
    void testInsertBookInCourseWithCourseNotPresent() throws BookException {
        try {
            InsertCourseBookController controller = new InsertCourseBookController();
            CourseBean courseBean = new CourseBean(0);
            BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
            controller.insertBookInCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene caricato correttamente");
        } catch (CourseException e) {
            Assertions.assertEquals(CourseNotFoundException.class, e.getCause().getClass());
        }
    }

    @Test
    void testInsertBookInCourseWithBookAlreadyPresent() throws BookException {
        try {
            InsertCourseBookController controller = new InsertCourseBookController();
            CourseBean courseBean = new CourseBean(1);
            BookBean bookBean = new BookBean("9788891904591", "Applicare UML e i pattern: analisi e progettazione orientata agli oggetti");
            controller.insertBookInCourse(courseBean, bookBean);
            Assertions.fail("Il libro viene caricato correttamente");
        } catch (CourseException e) {
            Assertions.assertEquals(BookAlreadyInCourseException.class, e.getCause().getClass());
        }
    }

}
