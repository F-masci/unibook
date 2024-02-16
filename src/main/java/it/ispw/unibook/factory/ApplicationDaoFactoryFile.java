package it.ispw.unibook.factory;

import it.ispw.unibook.dao.*;

/**
 * Specializza la classe astratta <i>ApplicationDaoFactory</i>.<br>
 * Si sfrutta il polimorfismo per scegliere a runtime quale famiglia di DAO utilizzare.<br>
 * Viene creata una sola istanza tra quelle figlie e viene scelto dal padre quale istanziare
 * a seconda dei parametri di configurazione del sistema
 */
public class ApplicationDaoFactoryFile extends ApplicationDaoFactory {

    @Override
    public LoginDao getLoginDao() {
        return LoginDaoAppFile.getInstance();
    }

    @Override
    public AccountDao getAccountDao() {
        return AccountDaoAppFile.getInstance();
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
