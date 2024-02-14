package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.AccountTypes;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LoginDaoAppFile implements LoginDao {

    // Unica istanza di DAO di login che sfrutta il file system
    private static LoginDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni
    private static final String FILE_NAME = "account.csv";

    // Descrittore del file
    private File fd;

    // Il costruttore è reso privato per applicate il pattern Singleton
    private LoginDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file
            fd = new File("csv/" + FILE_NAME);
            // Se il file non esiste Viene istanziato
            if (!fd.exists() && !fd.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Permette di ottenere l'unica istanza di factory per il DAO di login che sfrutta file system
     * @return DAO di login che utilizza il file system
     */
    public static LoginDaoAppFile getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new LoginDaoAppFile();
        return instance;
    }

    @Override
    public AccountEntity login(String email, String password) throws EmailNotFoundException, IncorrectPasswordException {

        // Account da restituire
        AccountEntity account = null;

        // Apre il file CSV
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Controlla che l'email inserita sia uguale a quella del record
                if (Objects.equals(tuple[AccountAttributesOrder.EMAIL.getIndex()], email)) {
                    // Controlla che la password inserita sia uguale a quella del record
                    if(Objects.equals(tuple[AccountAttributesOrder.PASSWORD.getIndex()], password)) {
                        // Viene istanziato il tipo relativo all'account loggato
                        AccountTypes type = AccountTypes.getFromString(tuple[AccountAttributesOrder.TYPE.getIndex()]);
                        // Viene istanziato l'entità relativa all'account loggato
                        account = new AccountEntity(
                                Integer.parseInt(tuple[AccountAttributesOrder.CODE.getIndex()]),
                                tuple[AccountAttributesOrder.EMAIL.getIndex()],
                                type
                        );
                    } else {    // Nel caso in cui la password non corrisponde solleva la relativa eccezione
                        throw new IncorrectPasswordException();
                    }
                }
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }

        // Nel caso non sia stata trovato alcun record con l'email inserita viene sollevata la relativa eccezione
        if(account == null) throw new EmailNotFoundException();

        // Viene ritornato l'account relativo alle credenziali inserite
        return account;

    }
}
