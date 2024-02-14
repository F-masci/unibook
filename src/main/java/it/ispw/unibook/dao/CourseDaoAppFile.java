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

    // Unica istanza di DAO dei corsi che sfrutta il file system
    private static CourseDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni sui libri
    private static final String BOOK_FILE_NAME = "book.csv";
    // Nome del file dove vengono salvate le informazioni sui libri in vendita
    private static final String SELLABLE_BOOK_FILE_NAME = "sellableBook.csv";

    private int autoIncrement = 0;

    // Descrittore del file dei libri
    private File fdBook;
    // Descrittore del file dei libri in vendita
    private File fdSellableBook;

    // Il costruttore è reso privato per applicate il pattern Singleton
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

    /**
     * Permette di ottenere l'unica istanza di DAO dei corsi che sfrutta JDBC
     * @return DAO dei corsi che utilizza JDBC
     */
    public static CourseDaoAppFile getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new CourseDaoAppFile();
        return instance;
    }

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        // Apre il file in scrittura in modalità append
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fdBook, true)))) {
            // Scrive la nuova tupla a partire dall'entità
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
        // Contiene tutti i record attualmente presenti nel file
        List<String[]> records = new ArrayList<>();
        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdBook)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Se il record è differente dall'associazione che si vuole eliminare viene aggiunto alla lista
                if(Integer.parseInt(tuple[BookAttributesOrder.COURSE.getIndex()]) == course.getCode() &&
                        Objects.equals(tuple[BookAttributesOrder.ISBN.getIndex()], book.getIsbn())) continue;
                records.add(tuple);
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // I record vengono riscritti sul file escludendo quello che deve essere eliminato
        rewriteFile(fdBook, records);
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
