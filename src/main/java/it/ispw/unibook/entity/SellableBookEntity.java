package it.ispw.unibook.entity;

import it.ispw.unibook.dao.AccountDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.entity.state.book.sellable.SellableBookStateMachine;
import it.ispw.unibook.entity.state.book.sellable.SellableBookStateMachineGoF;
import it.ispw.unibook.exceptions.book.sellable.SellableBookAlreadySoldException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotSoldExceptions;
import it.ispw.unibook.exceptions.negotiation.BuyerAlreadyInNegotiationException;
import it.ispw.unibook.exceptions.negotiation.BuyerNotInNegotiationException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Rappresenta il concetto di libro in vendita
 * Estende il concetto generale di Libro
 */
public class SellableBookEntity extends BookEntity {

    // Codice del libro in vendita
    private final int code;
    // Costo del libro in vendita
    private final float price;
    // Account del venditore del libro
    private final AccountEntity seller;
    // Acquirenti del libro in vendita
    private List<AccountEntity> buyers = null;
    // Stato del libro
    // Permette di discriminare se il libro è stato venduto o meno
    private final SellableBookStateMachine state = new SellableBookStateMachineGoF();

    /**
     * Costruttore di default dell'entità
     * @param code Codice del libro in vendita
     * @param isbn ISBN del libro di cui questo è una copia in vendita
     * @param title Titolo del libro di cui questo è una copia in vendita
     * @param price Prezzo del libro in vendita
     * @param seller Account del venditore del libro
     * @param buyer Acquirente effettivo del libro
     * @param courseCode Codice del corso in cui questo libro è venduto
     */
    public SellableBookEntity(int code, String isbn, String title, float price, AccountEntity seller, AccountEntity buyer, int courseCode) {
        super(isbn, title, courseCode);
        this.code = code;
        this.price = price;
        this.seller = seller;
        if(buyer != null) {
            try {
                this.markAsSold(buyer);
            } catch (SellableBookAlreadySoldException ignored) {
                // Quest'eccezione non verrà mai sollevata poiché nello stato iniziale il libro non può essere già venduto
            }
        }
    }

    /**
     * Costruttore alternativo dell'entità che utilizza solo codice, isbn, titolo, prezzo e venditore
     * @param code Codice del libro in vendita
     * @param isbn ISBN del libro di cui questo è una copia in vendita
     * @param title Titolo del libro di cui questo è una copia in vendita
     * @param price Prezzo del libro in vendita
     * @param seller Account del venditore del libro
     */
    public SellableBookEntity(int code, String isbn, String title, float price, AccountEntity seller) {
        this(code, isbn, title, price, seller, null, 0);
    }

    /**
     * Costruttore alternativo dell'entità che utilizza solo isbn, prezzo e venditore
     * @param isbn ISBN del libro di cui questo è una copia in vendita
     * @param price Prezzo del libro in vendita
     * @param seller Account del venditore del libro
     */
    public SellableBookEntity(String isbn, float price, AccountEntity seller) {
        this(0, isbn, null, price, seller, null, 0);
    }

    /**
     * Costruttore alternativo dell'entità che utilizza solo il codice del libro in vendita
     * @param code Codice del libro in vendita
     */
    public SellableBookEntity(int code) {
        this(code, null, null, -1, null, null, 0);
    }

    /**
     *
     * @return Codice del libro in vendita
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @return Prezzo del libro in vendita
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @return Account del venditore del libro
     */
    public AccountEntity getSeller() {
        return seller;
    }

    /**
     *
     * @return Acquirenti del libro in vendita
     */
    public List<AccountEntity> getBuyers() {
        if(buyers == null) loadNegotiations();
        return buyers;
    }

    /**
     * Elimina gli acquirenti del libro in vendita
     */
    public void clearBuyers() {
        // Crea un copia locale della lista degli acquirenti
        List<AccountEntity> buyersCopy = new ArrayList<>(this.getBuyers());
        // Scorre la lista e rimuove il libro sulla lista dell'entità
        for(AccountEntity b: buyersCopy) {
            try {
                this.removeBuyer(b);
            } catch(BuyerNotInNegotiationException ignored) {
                // Quest'eccezione non viene mai sollevata
            }
        }
    }

    /**
     * Aggiunge un acquirente al libro
     * @param buyer Acquirente da aggiungere
     * @throws BuyerAlreadyInNegotiationException Viene sollevata se l'acquirente è già presente
     */
    public void addBuyer(@NotNull AccountEntity buyer) throws BuyerAlreadyInNegotiationException {
        // Viene caricata la lista degli acquirenti
        loadNegotiations();
        // Viene controllato se l'acquirente è già presente
        if(buyers.contains(buyer)) throw new BuyerAlreadyInNegotiationException();
        // Aggiunge l'acquirente alla lista
        buyers.add(buyer);
        // Salva in persistenza l'acquirente
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.addBuyerToSellableBookNegotiation(this, buyer);
    }

    /**
     * Rimuove l'acquirente dal libro
     * @param buyer Acquirente da rimuovere
     * @throws BuyerNotInNegotiationException Viene sollevata se l'acquirente non è presente
     */
    public void removeBuyer(@NotNull AccountEntity buyer) throws BuyerNotInNegotiationException {
        // Viene caricata la lista degli acquirenti
        loadNegotiations();
        // Viene controllato se l'acquirente è effettivamente presente
        if(!buyers.contains(buyer)) throw new BuyerNotInNegotiationException();
        // Rimuove l'acquirente alla lista
        buyers.remove(buyer);
        // Rimuove sulla persistenza l'acquirente
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.removeBuyerFromSellableBookNegotiation(this, buyer);
    }

    /**
     *
     * @return <i>True</i> se il libro è stato venduto.<br>
     *         <i>False</i> altrimenti.
     */
    public boolean isSold() {
        return state.isSold();
    }

    /**
     *
     * @return <i>True</i> se il libro none è ancora stato venduto.<br>
     *         <i>False</i> altrimenti.
     */
    public boolean isForSale() {
        return state.isForSale();
    }

    /**
     * Imposta il libro come venduto a un acquirente
     * @param buyer Acquirente a cui è stato effettivamente venduto il libro
     * @throws SellableBookAlreadySoldException Viene sollevata se il libro è già stato venduto
     */
    public void markAsSold(@NotNull AccountEntity buyer) throws SellableBookAlreadySoldException {
        // Lo stato del libro viene impostato come 'Venduto'
        state.markAsSold(buyer);
        // Salva in persistenza l'acquirente effettivo del libro
        SellableBookDao dao = ApplicationDaoFactory.getInstance().getSellableBookDao();
        dao.setBuyerToSellableBook(this, buyer);
    }

    /**
     *
     * @return Acquirente effettivo del libro
     * @throws SellableBookNotSoldExceptions Viene sollevata se il libro non è stato ancora venduto
     */
    public AccountEntity getBuyer() throws SellableBookNotSoldExceptions {
        return state.getBuyer();
    }

    /**
     * Carica, se non presente, la lista degli acquirenti del libro in vendita
     */
    private void loadNegotiations() {
        if(buyers == null) {
            AccountDao dao = ApplicationDaoFactory.getInstance().getAccountDao();
            buyers = dao.retrieveBuyersBySellableBook(this);
        }
    }

    /**
     * Due libri in vendita risultano uguali se hanno lo stesso codice o, se confrontato con un BookEntity, se hanno lo stesso ISBN.
     * @param o Oggetto da confrontare
     * @return <i>true</i> se gli oggetti sono uguali.
     *         <i>false</i> se gli oggetti sono diversi.
     */
    @Override
    public boolean equals(Object o) {
        // Se l'oggetto è quello corrente sono uguali
        if (this == o) return true;
        // Se l'oggetto è un'istanza di BookEntity e hanno lo stesso ISBN allora sono uguali
        if(o instanceof BookEntity book && Objects.equals(book.getIsbn(), this.getIsbn())) return true;
        // Se l'oggetto è un'istanza diversa da SellableBookEntity o ha un codice uguale a 0 allora sono diversi
        if (!(o instanceof SellableBookEntity sellableBook) || sellableBook.getCode() == 0) return false;
        // Confronto i codici per capire se sono uguali
        return  Objects.equals( ((SellableBookEntity) o).getCode(), this.getCode() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode());
    }

}
