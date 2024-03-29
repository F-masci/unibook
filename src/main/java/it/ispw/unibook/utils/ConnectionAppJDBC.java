package it.ispw.unibook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Questa classe implementa il pattern singleton per collegarsi
 * al database locale del sistema sfruttando JDBC
 */
public class ConnectionAppJDBC {

    private static Connection instance = null;

    private ConnectionAppJDBC() {}

    public static Connection getInstance() {
        if(instance == null) {
            try {
                Properties config = ApplicationProperties.getApplicationProperties();

                String dbms = config.getProperty("jdbc.dbms");
                String host = config.getProperty("jdbc.host");
                String port = config.getProperty("jdbc.port");
                String database = config.getProperty("jdbc.database");
                String username = config.getProperty("jdbc.username");
                String password = config.getProperty("jdbc.password");

                String url = "jdbc:" + dbms + "://" + host + ":" + port + "/" + database;
                instance = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                Printer.error(e);
                System.exit(-1);
            }
        }
        return instance;
    }

}
