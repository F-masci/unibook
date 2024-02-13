package it.ispw.unibook.controller.graphics.cli.professor;

import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.professor.PageBooksListCLI;
import it.ispw.unibook.view.cli.professor.PageInsertBookCLI;
import it.ispw.unibook.view.cli.professor.PageRemoveBookCLI;

public class HomeCLI {

    /**
     * Mostra la pagina con la lista dei libri per un corso
     */
    public void showBooks() {
        changeView(new PageBooksListCLI());
    }

    /**
     * Mostra la pagina di inserimento di un libro a un corso
     */
    public void addBook() {
        changeView(new PageInsertBookCLI());
    }

    /**
     * Mostra la pagina di rimozioni di un libro a un corso
     */
    public void deleteBook() {
        changeView(new PageRemoveBookCLI());
    }

    /**
     * Permette di cambiare la View attuale
     * @param page Pagina da visualizzare
     */
    private void changeView(PageCLI page) {
        page.display();
    }

}
