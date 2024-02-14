package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;

/**
 * Specializza la classe astratta <i>ApplicationDaoFactory</i>.<br>
 * Si sfrutta il polimorfismo per scegliere a runtime quale famiglia di DAO utilizzare.<br>
 * Viene creata una sola istanza tra quelle figlie e viene scelto dal padre quale istanziare
 * a seconda dei parametri di configurazione del sistema
 */
public class ApplicationDaoFactoryJDBC extends ApplicationDaoFactory {

    // Unica istanza di factory per la famiglia dei DAO che utilizzano JDBC
    private static ApplicationDaoFactoryJDBC instance = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private ApplicationDaoFactoryJDBC() {}

    /**
     * Permette di ottenere l'unica istanza di factory per la famiglia dei DAO che utilizzano JDBC
     * @return Factory per i DAO
     */
    public static ApplicationDaoFactoryJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new ApplicationDaoFactoryJDBC();
        return instance;
    }

    @Override
    public LoginDao getLoginDao() {
        return LoginDaoAppJDBC.getInstance();
    }

    @Override
    public CourseDao getCourseDao() {
        return CourseDaoAppJDBC.getInstance();
    }

    @Override
    public AccountDao getAccountDao() {
        return new AccountDaoAppJDBC();
    }

    @Override
    public BookDao getBookDao() {
        return BookDaoAppJDBC.getInstance();
    }

    @Override
    public SellableBookDao getSellableBookDao() {
        return SellableBookDaoAppJDBC.getInstance();
    }

}
