package it.ispw.unibook.view.cli.student;

import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.student.GenericStudentPageCLI;
import it.ispw.unibook.view.cli.PageCLI;

import java.io.IOException;

public class PagePurchaseBookCLI extends GenericStudentPageCLI implements PageCLI {

    @Override
    public void display() {

        Printer.clear();
        Printer.println("-- PAGINA ACQUISTO LIBRO --");

        int selection;

        Printer.println("""
                [0] Esci
                [1] Ricerca globale
                [2] Cerca nel corso""");

        while (true) {

            try {

                Printer.print("Azione: ");
                selection = Integer.parseInt(br.readLine());
                switch (selection) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> showGlobalSearch();
                    case 2 -> showCourseSearch();
                    default -> throw new SelectionNotValidException();
                }

                break;

            } catch (IOException e) {
                Printer.error(e);
                System.exit(-1);
            } catch (NumberFormatException e) {
                showErrorMessage("L'input inserito non è un numero");
            } catch (SelectionNotValidException e) {
                showErrorMessage(e);
            }
        }

    }

    private void showGlobalSearch() {

        String isbn = requestBookCode();

    }

    private void showCourseSearch() {

    }

}
