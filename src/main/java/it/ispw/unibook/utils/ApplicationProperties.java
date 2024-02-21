package it.ispw.unibook.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private static final String PROPERTIES_FILE = "/it/ispw/unibook/app.properties";
    private static Properties config = null;
    private ApplicationProperties() {}

    public static Properties getApplicationProperties() {
        try(InputStream prop = ApplicationProperties.class.getResourceAsStream(PROPERTIES_FILE)) {
            if(config == null) {
                config = new Properties();
                config.load(prop);
            }
        } catch(IOException e) {
            Printer.error("Errore durante la lettura del file di configurazione", e);
            System.exit(-1);
        }
        return config;
    }

}
