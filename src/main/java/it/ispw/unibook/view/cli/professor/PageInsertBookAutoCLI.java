package it.ispw.unibook.view.cli.professor;

import it.ispw.unibook.controller.graphics.cli.professor.InsertBookAutoCLI;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PageInsertBookAutoCLI {

    private InsertBookAutoCLI controller = new InsertBookAutoCLI();

    public void display() {

        Printer.clear();

        Printer.println("--- INSERIMENTO AUTOMATICO LIBRO ---");
        Printer.println("Inserisci l'ISBN del libro che vuoi aggiungere alla lista oppure digita esc per tornare indietro");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ISBN;

        try {
            Printer.print("ISBN: ");
            ISBN = br.readLine();

            if(ISBN.equals("esc")) return;

            String book = controller.insertBook(ISBN);
            Printer.println("Libro inserito: " + book);

        } catch (IOException e) {
            Printer.error(e);
        } catch (NumberFormatException e) {
            Printer.error("Il codice inserito non è un numero");
        }

    }

}
