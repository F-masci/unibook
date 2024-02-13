package it.ispw.unibook.controller.graphics.cli.student;

import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.student.*;

public class HomeCLI {

    /**
     * Mostra la pagina con la lista dei corsi
     */
    public void showCourses() { changeView(new PageCoursesListCLI()); }

    /**
     * Mostra la pagina con la lista dei libri per un corso
     */
    public void showBooks() { changeView(new PageBooksListCLI()); }

    /**
     * Mostra la pagina con il menù per le trattative attive
     */
    public void showActiveSellableBooks() { changeView(new PageActiveNegotiationCLI()); }

    /**
     * Mostra la pagina per iniziare l'acquisto di un libro
     */
    public void showPurchaseBook() { changeView(new PagePurchaseBookCLI()); }

    /**
     * Mostra la pagina per inserire un libro in vendita
     */
    public void showInsertSellableBook() { changeView(new PageInsertSellableBookCLI()); }

    /**
     * Mostra la pagina per rimuovere un libro in vendita
     */
    public void showRemoveSellableBook() { changeView(new PageRemoveSellableBookCLI()); }

    /**
     * Mostra la pagina per impostare un libro come venduto
     */
    public void showMarkSellableBookSold() { changeView(new PageMarkSellableBookSoldCLI()); }

    /**
     * Permette di cambiare la View attuale
     * @param page Pagina da visualizzare
     */
    private void changeView(PageCLI page) {
        page.display();
    }

}
