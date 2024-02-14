package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.exceptions.book.BookNotFoundException;

public interface LibraryDao {

    /**
     * Viene cercato un libro nella libreria tramite ISBN
     * @param isbn ISBN del libro da cercate
     * @return Libro trovato
     * @throws BookNotFoundException Viene sollevata se non è stato trovato alcun libro
     */
    BookEntity searchBookByISBN(String isbn) throws BookNotFoundException;

}
