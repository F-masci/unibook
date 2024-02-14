package it.ispw.unibook.factory;

import it.ispw.unibook.dao.LibraryDao;
import it.ispw.unibook.dao.LibraryDaoOL;

public class LibraryDaoFactory {

    // Unica istanza di factory per il DAO della libreria
    private static LibraryDaoFactory instance = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private LibraryDaoFactory() {}

    /**
     * Permette di ottenere l'unica istanza di factory per il DAO della libreria
     * @return DAO della libreria
     */
    public static LibraryDaoFactory getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new LibraryDaoFactory();
        return instance;
    }

    /**
     * Permette di ottenere l'istanza del DAO della libreria
     * @return DAO della libreria
     */
    public LibraryDao getDao() {
        return LibraryDaoOL.getInstance();
    }

}
