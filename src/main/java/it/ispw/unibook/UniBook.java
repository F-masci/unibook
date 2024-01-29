package it.ispw.unibook;

import it.ispw.unibook.controller.graphics.gui.ControllerGUI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import it.ispw.unibook.exceptions.cli.SelectionNotValidException;
import it.ispw.unibook.utils.Printer;
import it.ispw.unibook.view.cli.PageCLI;
import it.ispw.unibook.view.cli.PageLoginCLI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UniBook extends Application {

    /**
     * Punto di avvio del sistema.
     * Il metodo permette di scegliere il tipo di interfaccia grafica che si
     * vuole usare per interagire con il sistema
     * @param args Argomenti passati tramite riga di comando quando viene lanciata l'applicazione
     */
    public static void main(String[] args) {

        // FIXME: eliminare la scelta automatica
        // launch(args);
        // System.exit(0);

        Printer.println("Seleziona l'interfaccia che vuoi usare");
        Printer.println("\t[1] Interfaccia grafica");
        Printer.println("\t[2] Riga di comando");

         int selection = 2;
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            Printer.print("\nSelezione: ");
            try {
                // selection = Integer.parseInt(br.readLine());
                if(selection != 1 && selection != 2) throw new SelectionNotValidException();
                break;
            } /*catch (IOException e) {
                Printer.error("Errore durante la lettura dell'input");
                System.exit(-1);
            } */catch (NumberFormatException e) {
                Printer.error("L'input inserito non è valido");
            } catch (SelectionNotValidException e) {
                Printer.error(e);
            }
        }

        // Viene avviata l'interfaccia selezionata
        if(selection == 1) {
            launch(args);
        } else {
            PageCLI login = new PageLoginCLI();
            login.display();
        }

        System.exit(0);
    }

    @Override
    public void start(Stage stage) {

        Printer.println("Avvio GUI");
        stage.setTitle("UniBook");

        ControllerGUI.setStage(stage);
        ControllerGUI.setPage(PagesGUI.LOGIN);

    }

}
