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

    @Test
    void testRemoveBookFromCourseWithValivRemove() throws BookException, CourseException {
        RemoveCourseBookController controller = new RemoveCourseBookController();
        CourseBean courseBean = new CourseBean(1);
        BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
        Assertions.assertDoesNotThrow(() -> controller.removeBookFromCourse(courseBean, bookBean));
    }

    @Test
    void testRemoveBookFromCourseWithCourseNotPresent() throws BookException {
        try {
            RemoveCourseBookController controller = new RemoveCourseBookController();
            CourseBean courseBean = new CourseBean(0);
            BookBean bookBean = new BookBean(VALID_ISBN, VALID_TITLE);
            controller.removeBookFromCourse(courseBean, bookBean);
        } catch(CourseException e) {
            Assertions.assertEquals(CourseNotFoundException.class, e.getCause().getClass());
        }
    }

    @Test
    void testRemoveBookFromCourseWithBookNotInCourse() throws CourseException {
        try {
            RemoveCourseBookController controller = new RemoveCourseBookController();
            CourseBean courseBean = new CourseBean(1);
            BookBean bookBean = new BookBean("0000000000000");
            controller.removeBookFromCourse(courseBean, bookBean);
        } catch (BookException e) {
            Assertions.assertEquals(BookNotInCourseException.class, e.getCause().getClass());
        }
    }

}
