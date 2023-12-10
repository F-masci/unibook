package it.ispw.unibook.utils;

import java.sql.Connection;

/**
 * Questa classe implementa il pattern singleton per collegarsi
 * al database universitario sfruttando JDBC.
 * Poichè l'università non espone endpoint di collegamento pubblici
 * questa classe fornisce una connessione al database locale dell'applicazione
 */
public class ConnectionUniJDBC {

    private ConnectionUniJDBC() {}

    public static Connection getInstance() {
        return ConnectionAppJDBC.getInstance();
    }

}
