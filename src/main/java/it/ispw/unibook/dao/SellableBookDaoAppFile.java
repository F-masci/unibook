package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.exceptions.book.sellable.SellableBookNotFoundException;
import it.ispw.unibook.exceptions.course.CourseNotFoundException;
import it.ispw.unibook.factory.ApplicationDaoFactoryFile;
import it.ispw.unibook.factory.UniversityDaoFactory;
import it.ispw.unibook.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellableBookDaoAppFile implements SellableBookDao {

    // Unica istanza di DAO dei libri in vendita che sfrutta il file system
    private static SellableBookDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni dei libri in vendita
    private static final String SELLABLE_BOOK_FILE_NAME = "sellableBook.csv";
    // Nome del file dove vengono salvate le informazioni degli acquirenti di un libro
    private static final String NEGOTIATION_FILE_NAME = "negotiation.csv";

    private int autoIncrement = 0;

    // Descrittore del file dei libri in vendita
    private File fdSellableBook;
    // Descrittore del file degli acquirenti dei libri
    private File fdNegotiation;

    private SellableBookDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file dei libri in vendita
            fdSellableBook = new File("csv/" + SELLABLE_BOOK_FILE_NAME);
            // Se il file non esiste Viene istanziato
            if (!fdSellableBook.exists() && !fdSellableBook.createNewFile()) throw new IOException();
            // Istanzia il descrittore del file relativo al file degli acquirenti dei libri
            fdNegotiation = new File("csv/" + NEGOTIATION_FILE_NAME);
            // Se il file non esiste Viene istanziato
            if (!fdNegotiation.exists() && !fdNegotiation.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Permette di ottenere l'unica istanza di DAO dei libri in vendita che sfrutta il file system
     * @return DAO dei libri in vendita che utilizza il file system
     */
    public static SellableBookDaoAppFile getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new SellableBookDaoAppFile();
        return instance;
    }

    @Override
    public SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException {
        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file per cercare quello richiesto
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]) == code)
                    // Sfrutta la funzione ausiliare per creare l'entità a partire dalla tupla
                    return createEntityFromTuple(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Viene sollevata se il libro in vendita non è stato trovato
        throw new SellableBookNotFoundException();
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Controlla se il record è relativo a un libro in vendita che è una copia del libro avente l'ISBN fornito
                // In caso lo sia viene aggiunto alla lista
                if(Objects.equals(tuple[SellableBookAttributesOrder.ISBN.getIndex()], isbn))
                    sellableBooks.add(createEntityFromTuple(tuple));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Viene ritornata la lista
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity seller) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Controlla se il record è relativo a un libro in vendita del venditore fornito
                // In caso lo sia viene aggiunto alla lista
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.SELLER.getIndex()]) == seller.getCode())
                    sellableBooks.add(createEntityFromTuple(tuple));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Viene ritornata la lista
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdNegotiation)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Controlla se il record è relativo a un libro che l'acquirente è intenzionato ad acquistare
                // In caso lo sia viene aggiunto alla lista
                if(Integer.parseInt(tuple[NegotiationAttributesOrder.STUDENT.getIndex()]) == negotiationBuyer.getCode())
                    sellableBooks.add(retrieveSellableBookByCode(Integer.parseInt(tuple[NegotiationAttributesOrder.BOOK.getIndex()])));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        } catch (SellableBookNotFoundException e) {
            // Ignorata poiché il libro esiste sicuramente
        }
        // Viene ritornata la lista
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course) {

        // Lista dei libri in vendita da restituire
        List<SellableBookEntity> sellableBooks = new ArrayList<>();

        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Controlla se il record è relativo a un libro in vendita nel corso fornito
                // In caso lo sia viene aggiunto alla lista
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.COURSE.getIndex()]) == course.getCode())
                    sellableBooks.add(createEntityFromTuple(tuple));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Viene ritornata la lista
        return sellableBooks;
    }

    @Override
    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook) {
        // Viene caricato il prossimo codice da usare
        loadAutoIncrement();
        // Apre il file in scrittura in modalità append
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdSellableBook, true)))) {
            // Scrive la nuova tupla a partire dall'entità
            csvWriter.writeNext(new String[] {
                    String.valueOf(autoIncrement),
                    String.valueOf(course.getCode()),
                    sellableBook.getIsbn(),
                    String.valueOf(sellableBook.getSeller().getCode()),
                    String.valueOf(sellableBook.getPrice()),
                    "0"
            });
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook) {
        // Contiene tutti i record attualmente presenti nel file
        List<String[]> records = new ArrayList<>();
        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Se il record è differente dall'associazione che si vuole eliminare viene aggiunto alla lista
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]) == sellableBook.getCode()) continue;
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // I record vengono riscritti sul file escludendo quello che deve essere eliminato
        rewriteFile(fdSellableBook, records);
    }

    @Override
    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        // Apre il file in scrittura in modalità append
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdNegotiation, true)))) {
            // Scrive la nuova tupla a partire dall'entità
            csvWriter.writeNext(new String[] {
                    String.valueOf(sellableBook.getCode()),
                    String.valueOf(buyer.getCode())
            });
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        // Contiene tutti i record attualmente presenti nel file
        List<String[]> records = new ArrayList<>();
        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdNegotiation)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Se il record è differente dall'associazione che si vuole eliminare viene aggiunto alla lista
                if(Integer.parseInt(tuple[NegotiationAttributesOrder.BOOK.getIndex()]) == sellableBook.getCode() &&
                        Integer.parseInt(tuple[NegotiationAttributesOrder.STUDENT.getIndex()]) == buyer.getCode()) continue;
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // I record vengono riscritti sul file tenendo conto della modifica
        rewriteFile(fdNegotiation, records);
    }

    @Override
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer) {
        // Contiene tutti i record attualmente presenti nel file
        List<String[]> records = new ArrayList<>();
        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Viene cercato il record da modificare e, una volta modificato, viene aggiunto alla lista
                // Tutti gli altri record vengono aggiunti senza modifiche
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]) == sellableBook.getCode())
                    tuple[SellableBookAttributesOrder.BUYER.getIndex()] = String.valueOf(buyer.getCode());
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // I record vengono riscritti sul file tenendo conto della modifica
        rewriteFile(fdSellableBook, records);
    }

    /**
     * Funzione ausiliare per istanziare un'entità a partire da una tupla di libro in vendita
     * @param tuple Tupla da cui estrarre i dati
     * @return Entità con i dati presenti nella tupla
     */
    private SellableBookEntity createEntityFromTuple(String[] tuple) {
        // Entità da restituire
        SellableBookEntity sellableBook = null;
        try {
            // Viene istanziato il DAO relativo agli account per ottenere le informazioni su venditore e acquirente effettivo
            AccountDao accountDao = ApplicationDaoFactoryFile.getInstance().getAccountDao();

            // Viene estratto il codice dell'account del venditore
            int sellerCode = Integer.parseInt(tuple[SellableBookAttributesOrder.SELLER.getIndex()]);
            // Viene cercato sulla persistenza l'account del venditore
            AccountEntity seller = accountDao.retrieveAccountByCode(sellerCode);

            // Viene estratto il codice dell'account dell'acquirente effettivo
            int buyerCode = Integer.parseInt(tuple[SellableBookAttributesOrder.BUYER.getIndex()]);
            AccountEntity buyer = null;
            // Se il codice è diverso da 0 il venditore è effettivamente presente
            if (buyerCode != 0) {
                // Viene cercato sulla persistenza l'account dell'acquirente effettivamente presente
                buyer = accountDao.retrieveAccountByCode(buyerCode);
            }

            // Viene estratto l'ISBN del libro di cui quello in vendita è una copia per cercare sulla persistenza il libro
            String isbn = tuple[SellableBookAttributesOrder.ISBN.getIndex()];

            // Viene istanziato il DAO per ottenere il corso a cui il libro in vendita appartiene
            UniversityDao universityDao = UniversityDaoFactory.getInstance().getDao();
            // Viene cercato sulla persistenza il corso a cui il libro in vendita appartiene
            CourseEntity course = universityDao.retrieveCourseByCode(Integer.parseInt(tuple[SellableBookAttributesOrder.COURSE.getIndex()]));
            // Viene estratta la lista dei libri
            List<BookEntity> books = course.getBooks();
            // Viene ottenuto, dalla lista dei libri, quello di cui il libro in vendita è una copia
            BookEntity book = books.get(books.indexOf(new BookEntity(isbn)));

            // Viene istanziata l'entità del libro in vendita
            sellableBook = new SellableBookEntity(
                    Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]),
                    book.getIsbn(),
                    book.getTitle(),
                    Float.parseFloat(tuple[SellableBookAttributesOrder.PRICE.getIndex()]),
                    seller,
                    buyer,
                    Integer.parseInt(tuple[SellableBookAttributesOrder.COURSE.getIndex()])
            );
        } catch (AccountNotFoundException | CourseNotFoundException ignored) {
            // Ignorate poiché non potranno mai essere sollevate
        }
        // Viene resituita l'entità
        return sellableBook;
    }

    /**
     * Funzione ausiliare per calcolare il prossimo codice per il libro in vendita
     */
    private void loadAutoIncrement() {
        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {
            String[] tuple;
            // Legge i codici presenti e imposta il prossimo codice al successivo
            while ((tuple = csvReader.readNext()) != null) autoIncrement = Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]);
            autoIncrement++;
        } catch(IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Riscrive l'intero file a partire dalle tuple fornite
     * @param f File da riscrivere
     * @param records Lista dei records
     */
    private void rewriteFile(File f, List<String[]> records) {
        // Apre il file in scrittura
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(f)))) {
            // Scrive tutto il file
            csvWriter.writeAll(records);
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}