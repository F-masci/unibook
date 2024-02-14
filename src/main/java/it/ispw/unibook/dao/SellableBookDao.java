package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;

import java.util.List;

public interface SellableBookDao {

    /**
     * Cerca il libro in vendita sullo strato di persistenza attraverso il codice
     * @param code Codice del libro in vendita
     * @return Libro in vendita corrispondente
     * @throws SellableBookNotFoundException Viene sollevata se il libro in vendita non è stato trovato
     */
    SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException;

    /**
     * Cerca i libri in vendita sulla persistenza attraverso l'ISBN del libro di cui sono la copia
     * @param isbn ISBN del libro di cui quelli in vendita sono la copia
     * @return Lista dei libri in vendita con quell'ISBN
     */
    List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn);

    /**
     * Cerca i libri in vendita di uno studente sullo strato di persistenza
     * @param seller Account del venditore
     * @return Lista dei libri in vendita associati al venditore
     */
    List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity seller);

    /**
     * Cerca i libri in vendita che un acquirente è interessato ad acquistare
     * @param negotiationBuyer Account dell'acquirente
     * @return Lista dei libri che l'acquirente è interessato ad acquistare
     */
    List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer);

    /**
     * Cerca i libri in vendita associati a un corso sullo strato di persistenza
     * @param course Corso contenente i libri
     * @return Lista dei libri in vendita associati al corso
     */
    List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course);

    /**
     * Aggiunge un acquirente a un libro in vendita
     * @param sellableBook Libro in vendita
     * @param buyer Acquirente
     */
    void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);

    /**
     * Rimuove un acquirente da un libro in vendita
     * @param sellableBook Libro in vendita
     * @param buyer Acquirente
     */
    void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer);

    /**
     * Imposta il libro come effettivamente venduto a un acquirente
     * @param sellableBook Libro venduto all'acquirente
     * @param buyer Acquirente
     */
    void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer);

}
