package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;

/**
 * Specializza la classe astratta <i>ApplicationDaoFactory</i>.<br>
 * Si sfrutta il polimorfismo per scegliere a runtime quale famiglia di DAO utilizzare.<br>
 * Viene creata una sola istanza tra quelle figlie e viene scelto dal padre quale istanziare
 * a seconda dei parametri di configurazione del sistema
 */
public class ApplicationDaoFactoryFile extends ApplicationDaoFactory {

    // Unica istanza di factory per la famiglia dei DAO che utilizzano il file system
    private static ApplicationDaoFactoryFile instance = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private ApplicationDaoFactoryFile() {}

    /**
     * Permette di ottenere l'unica istanza di factory per la famiglia dei DAO che utilizzano il file system
     * @return Factory per i DAO
     */
    public static ApplicationDaoFactoryFile getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new ApplicationDaoFactoryFile();
        return instance;
    }

    @Override
    public LoginDao getLoginDao() {
        return LoginDaoAppFile.getInstance();
    }

    @Override
    public AccountDao getAccountDao() {
        return AccountDaoAppFile.getInstance();
    }

    @Override
    public CourseDao getCourseDao() {
        return CourseDaoAppFile.getInstance();
    }

    @Override
    public BookDao getBookDao() {
        return BookDaoAppFile.getInstance();
    }

    @Override
    public SellableBookDao getSellableBookDao() {
        return SellableBookDaoAppFile.getInstance();
    }

}
