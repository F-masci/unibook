package it.ispw.unibook.dao;

import it.ispw.unibook.entity.*;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.utils.ConnectionAppJDBC;
import it.ispw.unibook.utils.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoAppJDBC implements AccountDao {

    // Unica istanza di DAO degli account che sfrutta JDBC
    private static AccountDaoAppJDBC instance = null;

    // Connessione al database del sistema
    private Connection connection = null;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private AccountDaoAppJDBC() {
        connection = ConnectionAppJDBC.getInstance();
    }

    /**
     * Permette di ottenere l'unica istanza di DAO di account che sfrutta JDBC
     * @return DAO degli account che utilizza JDBC
     */
    public static AccountDaoAppJDBC getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new AccountDaoAppJDBC();
        return instance;
    }

    @Override
    public AccountEntity retrieveAccountByCode(int code) throws AccountNotFoundException {
        // Viene istanziato lo statement per eseguire le QUERY al database
        try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM account WHERE code=?")){
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, code);
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                // Viene creata l'entità a partire dal Result Set
                return new AccountEntity(
                        res.getInt("code"),
                        res.getString("email"),
                        AccountTypes.getFromString(res.getString("type")),
                        res.getString("name"),
                        res.getString("surname")
                );
            } else {
                // Se non ci sono risultati viene sollevata l'eccezione
                throw new AccountNotFoundException();
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return null;
    }

    @Override
    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook) {

        // Lista degli acquirenti da restituire
        List<AccountEntity> buyers = new ArrayList<>();

        // Viene istanziato lo statement per eseguire le QUERY al database
        try(PreparedStatement stm = connection.prepareStatement("SELECT * FROM view_negotiation WHERE book=?")) {
            // Viene impostato il parametro al posto del placeholder nello statement
            stm.setInt(1, sellableBook.getCode());
            // Viene eseguita la QUERY
            ResultSet res = stm.executeQuery();

            // Controlla che ci sia almeno un risultato
            if(res.first()) {
                do {
                    // Viene creata l'entità a partire dal Result Set
                    AccountEntity buyer = new AccountEntity(
                            res.getInt("studentCode"),
                            res.getString("studentEmail"),
                            AccountTypes.getFromString("Studente"),
                            res.getString("studentName"),
                            res.getString("studentSurname")
                    );
                    // L'entità creata viene aggiunta alla lista
                    buyers.add(buyer);
                } while (res.next());
            }
        } catch (SQLException e) {
            Printer.error(e);
            System.exit(-1);
        }

        // Viene ritornata la lista
        return buyers;

    }

}
