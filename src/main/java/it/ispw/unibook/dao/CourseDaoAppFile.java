package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDaoAppFile implements CourseDao {

    // Unica istanza nel sistema del DAO
    private static CourseDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni sui libri
    private static final String BOOK_FILE_NAME = "book.csv";
    // Nome del file dove vengono salvate le informazioni sui libri in vendita
    private static final String SELLABLE_BOOK_FILE_NAME = "sellableBook.csv";

    private int autoIncrement = 0;

    // Descrittore del file
    private File fdBook;
    private File fdSellableBook;

    private CourseDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file dei libri
            fdBook = new File("csv/" + BOOK_FILE_NAME);
            // Se il file dei libri non esiste Viene istanziato
            if (!fdBook.exists() && !fdBook.createNewFile()) throw new IOException();
            // Istanzia il descrittore del file relativo al file dei libri in vendita
            fdSellableBook = new File("csv/" + SELLABLE_BOOK_FILE_NAME);
            // Se il file dei libri in vendita non esiste Viene istanziato
            if (!fdSellableBook.exists() && !fdSellableBook.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    public static CourseDaoAppFile getInstance() {
        if(instance == null) instance = new CourseDaoAppFile();
        return instance;
    }

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdBook, true)))) {
            csvWriter.writeNext(new String[] {
                    String.valueOf(course.getCode()),
                    book.getIsbn(),
                    book.getTitle()
            });
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    @Override
    public void removeBookFromCourse(CourseEntity course, BookEntity book) {
        List<String[]> records = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[BookAttributesOrder.COURSE.getIndex()]) == course.getCode() &&
                        Objects.equals(tuple[BookAttributesOrder.ISBN.getIndex()], book.getIsbn())) continue;
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        rewriteFile(fdBook, records);
    }

    @Override
    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook) {
        loadAutoIncrement();
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdSellableBook, true)))) {
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
        List<String[]> records = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]) == sellableBook.getCode()) continue;
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        rewriteFile(fdSellableBook, records);
    }

    private void loadAutoIncrement() {
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdSellableBook)))) {
            String[] tuple;
            while ((tuple = csvReader.readNext()) != null) autoIncrement = Integer.parseInt(tuple[SellableBookAttributesOrder.CODE.getIndex()]);
            autoIncrement++;
        } catch(IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    private void rewriteFile(File f, List<String[]> records) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(f)))) {
            csvWriter.writeAll(records);
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
