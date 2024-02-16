package it.ispw.unibook.dao;

import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.utils.ConnectionUniJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseDaoAppJDBC implements CourseDao {

    // Unica istanza di DAO dei corsi che sfrutta JDBC
    private static CourseDaoAppJDBC instance = null;

    // Connessione al database del sistema
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private CourseDaoAppJDBC() {
        connection = ConnectionUniJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di DAO dei corsi che sfrutta JDBC
     * @return DAO dei corsi che utilizza JDBC
     */
    public static CourseDaoAppJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new CourseDaoAppJDBC();
        return instance;
    }



}
