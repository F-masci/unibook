package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;

/**
 * Specializza la classe astratta <i>ApplicationDaoFactory</i>.<br>
 * Si sfrutta il polimorfismo per scegliere a runtime quale famiglia di DAO utilizzare.<br>
 * Viene creata una sola istanza tra quelle figlie e viene scelto dal padre quale istanziare
 * a seconda dei parametri di configurazione del sistema
 */
public class ApplicationDaoFactoryJDBC extends ApplicationDaoFactory {

    @Override
    public LoginDao getLoginDao() {
        return LoginDaoAppJDBC.getInstance();
    }

    @Override
    public AccountDao getAccountDao() {
        return AccountDaoAppJDBC.getInstance();
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
