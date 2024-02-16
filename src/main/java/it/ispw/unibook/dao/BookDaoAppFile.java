package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDaoAppFile implements BookDao {

    // Unica istanza nel sistema del DAO dei libri
    private static BookDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni sui libri
    private static final String BOOK_FILE_NAME = "book.csv";

    // Descrittore del file
    private File fd;

    private BookDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file dei libri
            fd = new File("csv/" + BOOK_FILE_NAME);
            // Se il file dei libri non esiste Viene istanziato
            if (!fd.exists() && !fd.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Permette di ottenere l'unica istanza di DAO dei libri che sfrutta il file system
     * @return DAO di login che utilizza JDBC
     */
    public static BookDaoAppFile getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new BookDaoAppFile();
        return instance;
    }

    @Override
    public List<BookEntity> retrieveCourseBooks(CourseEntity course) {

        // Lista dei libri da restituire
        List<BookEntity> books = new ArrayList<>();

        // Apre il file in lettura
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[BookAttributesOrder.COURSE.getIndex()]) == course.getCode())
                    // Viene istanziata l'entità relativa al libro e viene aggiunta alla lista
                    books.add( new BookEntity(
                        tuple[BookAttributesOrder.ISBN.getIndex()],
                        tuple[BookAttributesOrder.TITLE.getIndex()]
                    ));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        // Viene ritornata la lista
        return books;
    }

    @Override
    public void addBookToCourse(CourseEntity course, BookEntity book) {
        // Apre il file in scrittura in modalità append
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)))) {
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
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

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
        // Apre il file in scrittura
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd)))) {
            // Scrive tutto il file
            csvWriter.writeAll(records);
        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

}
