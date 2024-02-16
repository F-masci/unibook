package it.ispw.unibook.controller.graphics.gui;

import it.ispw.unibook.bean.*;
import it.ispw.unibook.controller.application.LogoutController;
import it.ispw.unibook.exceptions.gui.ComboSelectionNotValidException;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.utils.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class GenericGUI {

    // Contiene l'associazione tra testo di una combo box e il suo valore
    private final ObservableMap<String, Integer> coursesCombo = FXCollections.observableHashMap();
    // Contiene l'associazione tra testo di una combo box e il suo valore
    private final ObservableMap<String, String> booksCombo = FXCollections.observableHashMap();
    // Contiene l'associazione tra testo di una combo box e il suo valore
    private final ObservableMap<String, Integer> sellableBooksCombo = FXCollections.observableHashMap();
    // Contiene l'associazione tra testo di una combo box e il suo valore
    private final ObservableMap<String, Integer> accountsCombo = FXCollections.observableHashMap();

    @FXML
    public void logout() {
        try {
            // Controller applicativo per eseguire il logout dal sistema
            LogoutController controller = new LogoutController();
            // Esegue il logout
            controller.logout(new Bean());
            // Torna alla pagina di login
            changePage(PagesGUI.LOGIN);
        } catch(SessionException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Carica una lista di corsi da un bean a una ComboBox
     * @param combo ComboBox su cui caricare la lista dei corsi
     * @param bean Contiene la lista dei corsi
     */
    protected void loadCoursesComboBox(@NotNull ComboBox<String> combo, @NotNull CoursesListBean bean) {
        // Viene estratta la lista dal bean
        List<CourseBean> course = bean.getList();
        // Vengono eliminati dalla combo i corsi già presente
        coursesCombo.clear();
        // Viene formattata la lista dei corsi
        // A ogni corso viene associato un codice
        for (CourseBean c : course) {
            this.coursesCombo.put(c.toString(), c.getCode());
        }
        // Vengono impostati i corsi sulla ComboBox
        combo.setItems(FXCollections.observableArrayList(this.coursesCombo.keySet()));
    }

    /**
     * Ritorna il codice del corso selezionato sulla combComboBox
     * @param combo Combo da cui estrarre il valore
     * @return Valore del corso selezionato
     * @throws ComboSelectionNotValidException Viene sollevata se la selezione non è valida poiché non ci sono valori corrispondenti
     */
    protected int getCourseSelectedFromComboBox(@NotNull ComboBox<String> combo) throws ComboSelectionNotValidException {
        // Viene estratto il valore dalla combo
        String value = combo.getValue();
        if (value != null) {
            // Viene confrontato con i codici presenti per ritornare quello selezionato
            return coursesCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }

    /**
     * Carica una lista di libri da un bean a una ComboBox
     * @param combo ComboBox su cui caricare la lista dei libri
     * @param bean Contiene la lista dei libri
     */
    protected void loadCourseBooksComboBox(@NotNull ComboBox<String> combo, @NotNull BooksListBean bean) {
        // Viene estratta la lista dal bean
        List<BookBean> books = bean.getList();
        // Vengono eliminati dalla combo i libri già presente
        booksCombo.clear();
        // Viene formattata la lista dei libri
        // A ogni libro viene associato un codice
        for(BookBean b: books) {
            this.booksCombo.put(b.getISBN() + " - " + b.toString(), b.getISBN());
        }
        combo.setItems(FXCollections.observableArrayList(booksCombo.keySet()));
    }

    /**
     * Ritorna il codice del corso selezionato sulla combComboBox
     * @param combo Combo da cui estrarre il valore
     * @return Valore del libro selezionato
     * @throws ComboSelectionNotValidException Viene sollevata se la selezione non è valida poiché non ci sono valori corrispondenti
     */
    protected String getCourseBookSelectedFromComboBox(@NotNull ComboBox<String> combo) throws ComboSelectionNotValidException {
        // Viene estratto il valore dalla combo
        String value = combo.getValue();
        if (value != null) {
            // Viene confrontato con i codici presenti per ritornare quello selezionato
            return booksCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }

    /**
     * Carica una lista di libri in vendita da un bean a una ComboBox
     * @param combo ComboBox su cui caricare la lista dei libri in vendita
     * @param bean Contiene la lista dei libri in vendita
     */
    protected void loadSellableBooksComboBox(@NotNull ComboBox<String> combo, @NotNull SellableBooksListBean bean) {
        // Viene estratta la lista dal bean
        List<SellableBookBean> sellableBooks = bean.getList();
        // Vengono eliminati dalla combo i libri in vendita già presente
        sellableBooksCombo.clear();
        // Viene formattata la lista dei libri in vendita
        // A ogni libro in vendita viene associato un codice
        for (SellableBookBean s : sellableBooks) {
            this.sellableBooksCombo.put(s.toString(), s.getCode());
        }
        combo.setItems(FXCollections.observableArrayList(this.sellableBooksCombo.keySet()));
    }

    /**
     * Ritorna il codice del libro in vendita selezionato sulla combComboBox
     * @param combo Combo da cui estrarre il valore
     * @return Valore del libro in vendita selezionato
     * @throws ComboSelectionNotValidException Viene sollevata se la selezione non è valida poiché non ci sono valori corrispondenti
     */
    protected int getSellableBookSelectedFromComboBox(@NotNull ComboBox<String> combo) throws ComboSelectionNotValidException {
        // Viene estratto il valore dalla combo
        String value = combo.getValue();
        if (value != null) {
            // Viene confrontato con i codici presenti per ritornare quello selezionato
            return sellableBooksCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }

    /**
     * Carica una lista di acquirenti per un libro in vendita da un bean a una ComboBox
     * @param combo ComboBox su cui caricare la lista degli acquirenti del libro in vendita
     * @param bean Contiene la lista degli acquirenti del libro in vendita
     */
    protected void loadAccountsComboBox(@NotNull ComboBox<String> combo, @NotNull AccountsListBean bean) {
        // Viene estratta la lista dal bean
        List<AccountBean> accounts = bean.getList();
        // Vengono eliminati dalla combo gli acquirenti del libro in vendita già presenti
        accountsCombo.clear();
        // Viene formattata la lista degli acquirenti del libro in vendita
        // A ogni acquirente viene associato un codice
        for (AccountBean a : accounts) {
            this.accountsCombo.put(a.toString(), a.getCode());
        }
        combo.setItems(FXCollections.observableArrayList(this.accountsCombo.keySet()));
    }

    /**
     * Ritorna il codice del'acquirente selezionato sulla combComboBox
     * @param combo Combo da cui estrarre il valore
     * @return Valore dell'acquirente selezionato
     * @throws ComboSelectionNotValidException Viene sollevata se la selezione non è valida poiché non ci sono valori corrispondenti
     */
    protected int getAccountSelectedFromComboBox(@NotNull ComboBox<String> combo) throws ComboSelectionNotValidException {
        // Viene estratto il valore dalla combo
        String value = combo.getValue();
        if (value != null) {
            // Viene confrontato con i codici presenti per ritornare quello selezionato
            return accountsCombo.get(value);
        }
        throw new ComboSelectionNotValidException();
    }

    /**
     * Carica una lista di libri di un corso presenti in un bean
     * @param list VBox per contenere la lista
     * @param bean Contiene la lista da visualizzare
     */
    protected void loadCourseBooksIntoList(@NotNull VBox list, @NotNull BooksListBean bean) {
        // Viene estratta la lista dal bean
        List<BookBean> books = bean.getList();
        // Vengono eliminati i libri già presenti
        list.getChildren().clear();
        // Viene mostrata la lista dei libri
        for (BookBean b : books) {
            String text = b.getISBN() + " - " + b.toString();
            Label label = new Label(text);
            label.getStyleClass().add("book");
            list.getChildren().add(label);
        }
    }

    /**
     * Modifica la View
     * @param page View da visualizzare
     */
    protected void changePage(PagesGUI page) {
        ControllerGUI.setPage(page);
    }

}
