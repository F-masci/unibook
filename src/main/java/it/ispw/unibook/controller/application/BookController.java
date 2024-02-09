package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.BookBean;
import it.ispw.unibook.bean.BooksListBean;
import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.exceptions.book.BookException;
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
     * @throws CourseException Viene sollevata in caso in cui il corso non viene trovato nello strato di persistenza
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
            try {
                // Viene creato il bean del libro a partire dall'entità
                BookBean book = createBeanFromEntity(b);
                // Si aggiunge il bean alla lista
                list.add(book);
            } catch (BookException ignored) {
                // Posso ignorare quest'eccezione poiché non verrà mai sollevata in quanto
                // i dati presi dalla persistenza presentano sempre un isbn valido
            }
        }
        // Viene impostata la lista nel bean
        bean.setList(list);
    }

    /**
     * Funzione ausiliare per formattare il bean a partire dall'entità
     * @param book Entità da cui creare il bean
     * @return Bean contenente i dati dell'entità
     * @throws BookException Non viene mai sollevata poiché i dati presi dalla
     * persistenza presentano sempre un isbn valido
     */
    private BookBean createBeanFromEntity(@NotNull BookEntity book) throws BookException {
        return new BookBean(
            book.getIsbn(),
            book.getTitle()
        );
    }

}
