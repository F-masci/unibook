package it.ispw.unibook;

import it.ispw.unibook.controller.graphics.cli.Printer;
import it.ispw.unibook.controller.graphics.gui.ControllerGUI;
import it.ispw.unibook.controller.graphics.cli.ControllerCLI;
import it.ispw.unibook.controller.graphics.gui.PagesGUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
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
        launch(args);
        System.exit(0);

        Printer.println("Seleziona l'interfaccia che vuoi usare");
        Printer.println("\t[1] Interfaccia grafica");
        Printer.println("\t[2] Riga di comando");

         int selection = 0;
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            Printer.print("\nSelezione: ");
            try {
                selection = Integer.parseInt(br.readLine());
                if(selection != 1 && selection != 2) {
                    Printer.error("Errore: numero non valido");
                    selection = 0;
                }
            } catch (IOException e) {
                Printer.error("Errore durante la lettura dell'input");
                System.exit(-1);
            } catch (NumberFormatException e) {
                Printer.error("Errore: l'input inserito non è un numero");
            }
        } while(selection == 0);

        // Viene avviata l'interfaccia selezionata
        if(selection == 1) launch(args);
        else ControllerCLI.getInstance().init();
    }

    @Override
    public void start(Stage stage) {

        Printer.println("Avvio GUI");
        stage.setTitle("UniBook");

        ControllerGUI controllerGUI = ControllerGUI.getInstance();
        controllerGUI.setStage(stage);
        controllerGUI.setPage(PagesGUI.LOGIN);

    }

}
