package it.ispw.unibook.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ApplicationProperties {

    private static Properties config = null;
    private ApplicationProperties() {};

    public static Properties getApplicationProperties() {
        try {
            if(config == null) {
                config = new Properties();
                FileInputStream fs = new FileInputStream("app.properties");
                config.load(fs);
            }
        } catch(IOException e) {
            Printer.error("Errore durante la lettura del file di configurazione", e);
            System.exit(-1);
        }
        return config;
    }

}
