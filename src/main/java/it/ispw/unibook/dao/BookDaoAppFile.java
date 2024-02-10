package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoAppFile implements BookDao {

    // Unica istanza nel sistema del DAO
    private static BookDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni sui libri
    private static final String BOOK_FILE_NAME = "book.csv";

    // Descrittore del file
    private File fd;

    private BookDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file dei libri
            fd = new File("csv/" + BOOK_FILE_NAME);
            // Se il file dei libri non esiste viene creato
            if (!fd.exists() && !fd.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    public static BookDaoAppFile getInstance() {
        if(instance == null) instance = new BookDaoAppFile();
        return instance;
    }

    @Override
    public List<BookEntity> retrieveCourseBooks(CourseEntity course) {
        List<BookEntity> books = new ArrayList<>();
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                if(Integer.parseInt(tuple[BookAttributesOrder.COURSE.getIndex()]) == course.getCode())
                    books.add( new BookEntity(
                        tuple[BookAttributesOrder.ISBN.getIndex()],
                        tuple[BookAttributesOrder.TITLE.getIndex()]
                    ));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return books;
    }

}
