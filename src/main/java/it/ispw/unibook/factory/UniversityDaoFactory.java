package it.ispw.unibook.factory;

import it.ispw.unibook.dao.UniversityDao;
import it.ispw.unibook.dao.UniversityDaoJDBC;

public class UniversityDaoFactory {

    // Unica istanza di factory per il DAO universitario
    private static UniversityDaoFactory instance = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private UniversityDaoFactory() {}

    /**
     * Permette di ottenere l'unica istanza di factory per il DAO universitario
     * @return DAO universitario
     */
    public static UniversityDaoFactory getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new UniversityDaoFactory();
        return instance;
    }

    /**
     * Permette di ottenere l'istanza del DAO universitario
     * @return DAO universitario
     */
    public UniversityDao getDao() {
        return UniversityDaoJDBC.getInstance();
    }

}
