package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.utils.Printer;

import java.io.*;
import java.util.List;

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
