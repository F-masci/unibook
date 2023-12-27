package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.ManageBookBean;
import it.ispw.unibook.controller.application.InsertCourseBookController;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.exceptions.book.ISBNNotValidException;

public class InsertBookCLI extends GenericControllerCLI {

    private final InsertCourseBookController controller = new InsertCourseBookController();

     public String getBookInformation(String ISBN) throws BookNotFoundException, ISBNNotValidException {
         BookBean bean = new BookBean(ISBN);
         controller.getBookInformation(bean);
         return bean.getName();
     }

     public void insertBook(int course, String ISBN, String name) throws ISBNNotValidException {
         ManageBookBean bean = new ManageBookBean(course, ISBN, name);
         controller.insertBook(bean);
     }

     public void insertBook(int course, BookBean bean) throws ISBNNotValidException {
        insertBook(course, bean.getISBN(), bean.getName());
     }

}
