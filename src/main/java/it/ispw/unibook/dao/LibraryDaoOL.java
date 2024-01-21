package it.ispw.unibook.dao;

import it.ispw.unibook.entity.BookEntity;
import it.ispw.unibook.exceptions.book.BookNotFoundException;
import it.ispw.unibook.utils.Printer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;

public class LibraryDaoOL implements LibraryDao {
    @Override
    public BookEntity searchBookByISBN(String ISBN) throws BookNotFoundException {

        try {
            
            String endpoint = "https://openlibrary.org/isbn/" + ISBN + ".json";
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(endpoint);
            HttpResponse response = client.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                StringReader stringReader = new StringReader(EntityUtils.toString(response.getEntity()));
                JsonObject json = Json.createReader(stringReader).readObject();
                String name = json.getString("title");
                return new BookEntity(ISBN, name);
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
