package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.AccountTypes;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoAppJDBC implements LoginDao {

    // Unica istanza di factory per il DAO di login che sfrutta JDBC
    private static LoginDaoAppJDBC instance = null;

    // Connessione al database del sistema
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private LoginDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di factory per il DAO di login che sfrutta JDBC
     * @return DAO di login che utilizza JDBC
     */
    public static LoginDaoAppJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new LoginDaoAppJDBC();
        return instance;
    }

    @Override
    public AccountEntity login(String email, String password) throws EmailNotFoundException, IncorrectPasswordException {

        // Account da restituire
        AccountEntity account = null;

        // Vengono istanziati gli statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM account WHERE email=? AND password=? LIMIT 1");
             PreparedStatement stmError = connection.prepareStatement("SELECT * FROM account WHERE email=? LIMIT 1")) {
            // Vengono impostati i parametri al posto dei placeholder nello statement
            stm.setString(1, email);
            stm.setString(2, password);
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if (!res.first()) {
                // Nel caso in cui non sia stato trovato nessun risultato, viene controllato se esiste almeno un account con la mail inserita
                stmError.setString(1, email);
                res = stmError.executeQuery();

                // Controllo che ci sia almeno un risultato
                if (!res.first()) {
                    // Se non trovo nessun risultato, sollevo l'eccezione per indicare che non c'è alcun account con l'email fornita
                    throw new EmailNotFoundException();
                } else {
                    // Altrimenti, se trovo un account con la mail fornita, sollevo l'eccezione per indicare che la password inserita è errata
                    throw new IncorrectPasswordException();
                }

            }
            // Viene istanziato il tipo relativo all'account loggatp
            AccountTypes type = AccountTypes.getFromString(res.getString("type"));
            // Viene istanziato l'entità relativa all'account loggato
            account = new AccountEntity(
                    res.getInt("code"),
                    res.getString("email"),
                    type
            );

        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        // Viene ritornato l'account relativo alle credenziali inserite
        return account;

    }
}
