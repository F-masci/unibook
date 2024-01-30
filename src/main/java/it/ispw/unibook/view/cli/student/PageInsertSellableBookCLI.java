package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.SellableBookBean;
import it.ispw.unibook.controller.graphics.cli.student.InsertSellableBookCLI;
import it.ispw.unibook.exceptions.book.BookException;
import it.ispw.unibook.exceptions.cli.EscCliException;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageManageSellableBookCLI;

import java.io.IOException;

public class PageInsertSellableBookCLI extends PageManageSellableBookCLI implements PageCLI {

    private final InsertSellableBookCLI controller = new InsertSellableBookCLI();

    @Override
    public void display() {

        Printer.clear();
        Printer.println("-- PAGINA INSERIMENTO LIBRO IN VENDITA --");

        super.printCoursesList(controller);
        int courseCode = requestCourseCode();

        try {
            super.printCourseBooksList(controller, courseCode);
        } catch (CourseException e) {
            showErrorMessage(e);
            return;
        }

        while(true) {
            try {
                String isbn = super.requestBookCode();
                BookBean bookBean = new BookBean(isbn);

                Printer.print("Prezzo di vendita: ");
                float price = Float.parseFloat(br.readLine());
                SellableBookBean sellableBookBean = new SellableBookBean(courseCode, bookBean, price);

                controller.insertSellableBook(sellableBookBean);
                Printer.println("Libro inserito correttamente in vendita");

                waitForExit();

                return;
            } catch(IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch(BookException | SessionException | CourseException e) {
                showErrorMessage(e);
            } catch (EscCliException e) {
                return;
            }
        }

    }
}
