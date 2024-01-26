package it.ispw.unibook.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    private static Properties config = null;
    private ApplicationProperties() {}

    public static Properties getApplicationProperties() {
        try(FileInputStream fs = new FileInputStream("app.properties")) {
            if(config == null) {
                config = new Properties();
                config.load(fs);
            }
        } catch(IOException e) {
            Printer.error("Errore durante la lettura del file di configurazione", e);
            System.exit(-1);
        }
        return config;
    }

}
