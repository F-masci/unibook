package it.ispw.unibook.utils;

import java.sql.Connection;

/**
 * Questa classe implementa il pattern singleton per collegarsi
 * al database universitario sfruttando JDBC.
 * Poiché l'università non espone endpoint di collegamento pubblici
 * questa classe fornisce una connessione al database locale dell'applicazione
 */
public class ConnectionUniJDBC {

    // Il costruttore è reso privato per applicate il pattern Singleton
    private ConnectionUniJDBC() {}

    /**
     * Permette di ottenere la connessione al server universitario
     * @return Connessione con il server universitario
     */
    public static Connection getInstance() {
        return ConnectionAppJDBC.getInstance();
    }

}
