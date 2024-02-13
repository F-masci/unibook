package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellableBookDaoAppFile implements SellableBookDao {

    // Unica istanza nel sistema del DAO
    private static SellableBookDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni
    private static final String FILE_NAME = "sellableBook.csv";

    // Descrittore del file
    private File fd;

    private SellableBookDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file
            fd = new File("csv/" + FILE_NAME);
            // Se il file non esiste viene creato
            if (!fd.exists() && !fd.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    public static SellableBookDaoAppFile getInstance() {
        if(instance == null) instance = new SellableBookDaoAppFile();
        return instance;
    }

    @Override
    public SellableBookEntity retrieveSellableBookByCode(int code) throws SellableBookNotFoundException {
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]) == code)
                    return createEntityFromTuple(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        throw new SellableBookNotFoundException();
    }

    @Override
    public List<SellableBookEntity> retrieveSellableBooksByIsbn(String isbn) {
        List<SellableBookEntity> sellableBooks = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

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
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

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
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course) {
        List<SellableBookEntity> sellableBooks = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

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
        // TODO
    }

    @Override
    public void removeBuyerFromSellableBookNegotiation(SellableBookEntity sellableBook, AccountEntity buyer) {
        // TODO
    }

    @Override
    public void setBuyerToSellableBook(SellableBookEntity sellableBook, AccountEntity buyer) {
        // TODO
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