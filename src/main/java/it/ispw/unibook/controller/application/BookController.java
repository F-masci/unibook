package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.course.CourseException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.factory.UniversityDaoFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller applicativo per l'implementazione delle operazioni comuni
 * sulle entità Libro richieste dai vari controller
 */
public class BookController {

    /**
     * Carica nel bean la lista dei libri associati al corso
     * @param bean Deve contenere il codice del corso. Contiene la lista dei libri collegati
     * @throws CourseException Viene sollevata nel caso in cui il corso non viene trovato nello strato di persistenza
     */
    public void retrieveBooksByCourse(@NotNull BooksListBean bean) throws CourseException {
        try {
            // Viene istanziato il DAO tramite factory per cercare il corso
            UniversityDao dao = UniversityDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il corso corrispondete al codice fornito
            // Se il corso non viene trovato viene sollevata l'eccezione
            CourseEntity course = dao.retrieveCourseByCode(bean.getCourseCode());
            // Si estrae la lista di libri dal corso e si inserisce all'interno del bean
            insertBooksListIntoBean(course.getBooks(), bean);
        } catch (CourseNotFoundException e) {
            // In caso in cui il corso non sia presente viene sollevata l'eccezione al chiamante
            throw new CourseException(e.getMessage(), e);
        }

    }

    /**
     * Funzione ausiliare per formattare la lista da inserire nel bean
     * a partire dalla lista di entità
     * @param books Lista di entità da cui creare la lista per il bean
     * @param bean Bean su cui caricare la lista
     */
    private void insertBooksListIntoBean(@NotNull List<BookEntity> books, @NotNull BooksListBean bean) {
        // Si prepara la lista da restituire al chiamante
        List<BookBean> list = new ArrayList<>();
        for (BookEntity b : books) {
            // Viene istanziato il bean del libro a partire dall'entità
            BookBean book = new BookBean(
                    b.getIsbn(),
                    b.getTitle()
            );
            // Si aggiunge il bean alla lista
            list.add(book);
        }
        // Viene impostata la lista nel bean
        bean.setList(list);
    }

}
