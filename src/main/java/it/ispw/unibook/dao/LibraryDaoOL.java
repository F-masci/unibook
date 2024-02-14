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
import java.sql.Connection;

public class LibraryDaoOL implements LibraryDao {

    // Unica istanza di factory per il DAO universitario
    private static LibraryDaoOL instance = null;

    private Connection connection = null;

    private LibraryDaoOL() {}

    public static LibraryDaoOL getInstance() {
        if(instance == null) instance = new LibraryDaoOL();
        return instance;
    }

    @Override
    public BookEntity searchBookByISBN(String isbn) throws BookNotFoundException {

        try {
            
            String endpoint = "https://openlibrary.org/isbn/" + isbn + ".json";
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(endpoint);
            HttpResponse response = client.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                StringReader stringReader = new StringReader(EntityUtils.toString(response.getEntity()));
                try(JsonReader jsonReader = Json.createReader(stringReader)) {
                    JsonObject json = jsonReader.readObject();
                    String name = json.getString("title");
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
