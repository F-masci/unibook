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
    private static final String NEGOTIATION_FILE_NAME = "sellableBook.csv";

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
        List<SellableBookEntity> sellableBooks = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Objects.equals(tuple[SellableBookAttributesOrder.ISBN.getIndex()], isbn))
                    sellableBooks.add(createEntityFromTuple(tuple));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity seller) {
        List<SellableBookEntity> sellableBooks = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.SELLER.getIndex()]) == seller.getCode())
                    sellableBooks.add(createEntityFromTuple(tuple));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByNegotiation(AccountEntity negotiationBuyer) {
        List<SellableBookEntity> sellableBooks = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdNegotiation)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[NegotiationAttributesOrder.STUDENT.getIndex()]) == negotiationBuyer.getCode())
                    sellableBooks.add(retrieveSellableBookByCode(Integer.parseInt(tuple[NegotiationAttributesOrder.BOOK.getIndex()])));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        } catch (SellableBookNotFoundException e) {
            // Ignorata
        }
        return sellableBooks;
    }

    @Override
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course) {
        List<SellableBookEntity> sellableBooks = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.COURSE.getIndex()]) == course.getCode())
                    sellableBooks.add(createEntityFromTuple(tuple));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return sellableBooks;
    }

    @Override
    public void addBuyerToSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdNegotiation, true)))) {
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
        List<String[]> records = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdNegotiation)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[NegotiationAttributesOrder.BOOK.getIndex()]) == sellableBook.getCode() &&
                        Integer.parseInt(tuple[NegotiationAttributesOrder.STUDENT.getIndex()]) == buyer.getCode()) continue;
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdNegotiation)))) {
            csvWriter.writeAll(records);
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer) {
        List<String[]> records = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]) == sellableBook.getCode())
                    tuple[SellableBookAttributesOrder.BUYER.getIndex()] = String.valueOf(buyer.getCode());
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdSellableBook)))) {
            csvWriter.writeAll(records);
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    private SellableBookEntity createEntityFromTuple(String[] tuple) {
        SellableBookEntity sellableBook = null;
        try {
            AccountDao accountDao = ApplicationDaoFactoryFile.getInstance().getAccountDao();

            int sellerCode = Integer.parseInt(tuple[SellableBookAttributesOrder.SELLER.getIndex()]);
            AccountEntity seller = accountDao.retrieveAccountByCode(sellerCode);

            int buyerCode = Integer.parseInt(tuple[SellableBookAttributesOrder.BUYER.getIndex()]);
            AccountEntity buyer = null;
            if (buyerCode != 0) {
                buyer = accountDao.retrieveAccountByCode(buyerCode);
            }

            String isbn = tuple[SellableBookAttributesOrder.ISBN.getIndex()];

            UniversityDao universityDao = UniversityDaoFactory.getInstance().getDao();
            CourseEntity course = universityDao.retrieveCourseByCode(Integer.parseInt(tuple[SellableBookAttributesOrder.COURSE.getIndex()]));
            List<BookEntity> books = course.getBooks();
            BookEntity book = books.get(books.indexOf(new BookEntity(isbn)));

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
            // Ignorata
        }
        return sellableBook;
    }
}