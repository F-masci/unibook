package it.ispw.unibook.factory;

import it.ispw.unibook.dao.AccountDao;
import it.ispw.unibook.dao.BookDao;
import it.ispw.unibook.dao.LoginDao;
import it.ispw.unibook.dao.SellableBookDao;
import it.ispw.unibook.exceptions.PersistenceNotValidException;
import it.ispw.unibook.utils.ApplicationProperties;

import java.util.Properties;

/**
 * Classe astratta che utilizza il polimorfismo per scegliere a runtime
 * quale tipo di persistenza usare
 */
public abstract class ApplicationDaoFactory {

    // Unica istanza di factory per le famiglie di DAO
    private static ApplicationDaoFactory instance = null;

    /**
     * Permette di ottenere l'unica istanza di factory per le famiglie di DAO
     * @return Factory per le famiglie di DAO
     */
    public static ApplicationDaoFactory getInstance() {
        try {
            // Se l'istanza non è presente viene creata
            if (instance == null) {
                // Viene caricato il file contenente la configurazione del sistema
                Properties config = ApplicationProperties.getApplicationProperties();
                // Viene letta la configurazione relativa alla persistenza
                // A seconda del valore viene istanziata una factory per una famiglia di DAO specifica
                switch (config.getProperty("persistence")) {
                    case "jdbc" -> instance = new ApplicationDaoFactoryJDBC();
                    case "file" -> instance = new ApplicationDaoFactoryFile();

                    default -> throw new PersistenceNotValidException();
                }
            }
        } catch(PersistenceNotValidException e) {
            // Nel caso la persistenza selezionata non sia valida viene impostata
            // di default quella del file system
            instance = new ApplicationDaoFactoryFile();
        }
        return instance;
    }

    /**
     * Permette di ottenere l'istanza del DAO di login
     * @return DAO di login
     */
    public abstract LoginDao getLoginDao();

    /**
     * Permette di ottenere l'istanza del DAO degli account
     * @return DAO degli account
     */
    public abstract AccountDao getAccountDao();

    /**
     * Permette di ottenere l'istanza del DAO dei libri
     * @return DAO dei libri
     */
    public abstract BookDao getBookDao();

    /**
     * Permette di ottenere l'istanza del DAO dei libri in vendita
     * @return DAO dei libri in vendita
     */
    public abstract SellableBookDao getSellableBookDao();
}
