package it.ispw.unibook.view.cli;

/**
 * Questa interfaccia dovrebbe essere implementata da tutte le pagine della view CLI.
 * Definisce l'unica funzione che viene chiamata per avviare la visualizzazione della View.
 */
public interface PageCLI {

    /**
     * Punto di accesso alla View. Chiamando questa funzione viene stampata la View
     */
    public void display();

}
