package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.utils.Printer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;

public class LibraryDaoOL implements LibraryDao {

    // Unica istanza di DAO di libreria che sfrutta JDBC
    private static LibraryDaoOL instance = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private LibraryDaoOL() {}

    /**
     * Permette di ottenere l'unica istanza di DAO di libreria che sfrutta JDBC
     * @return DAO di libreria che utilizza JDBC
     */
    public static LibraryDaoOL getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new LibraryDaoOL();
        return instance;
    }

    @Override
    public BookEntity searchBookByISBN(String isbn) throws BookNotFoundException {
        try {
            // Viene costruito l'endpoint a ui effettuare la richiesta
            String endpoint = "https://openlibrary.org/isbn/" + isbn + ".json";
            // Viene istanziato il client per eseguire la richiesta
            HttpClient client = HttpClientBuilder.create().build();
            // Viene istanziata la richiesta da esseguire tramite metodo GET all'endpoint costruito
            HttpGet getRequest = new HttpGet(endpoint);
            // Viene eseguita la richiesta
            HttpResponse response = client.execute(getRequest);
            // Si controlla lo status della risposta
            // Se lo stato è diverso da 200 il libro non è stato trovato
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Si utilizzano delle classi ausiliare per leggere la risposta ed estrarre le informazioni del libro
                StringReader stringReader = new StringReader(EntityUtils.toString(response.getEntity()));
                try(JsonReader jsonReader = Json.createReader(stringReader)) {
                    JsonObject json = jsonReader.readObject();
                    // Si estraggono dalla risposta le informazioni necessarie
                    String name = json.getString("title");
                    // Viene ritornata l'entità con le informazioni trovate
                    return new BookEntity(isbn, name);
                }
            } else {
                throw new BookNotFoundException();
            }

        } catch (IOException e) {
            Printer.error(e);
            System.exit(-1);
        }

        return null;

    }

}
