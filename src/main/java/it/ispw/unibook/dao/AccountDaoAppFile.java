package it.ispw.unibook.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.AccountTypes;
import it.ispw.unibook.entity.SellableBookEntity;
import it.ispw.unibook.exceptions.account.AccountNotFoundException;
import it.ispw.unibook.utils.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoAppFile implements AccountDao {

    // Unica istanza nel sistema del DAO degli account che sfrutta il file system
    private static AccountDaoAppFile instance = null;
    // Nome del file dove vengono salvate le informazioni sugli account
    private static final String ACCOUNT_FILE_NAME = "account.csv";
    // Nome del file dove vengono salvate le informazioni sugli acquirenti di un libro
    private static final String NEGOTIATION_FILE_NAME = "negotiation.csv";

    // Descrittore del file degli account
    private File fdAccount;
    // Descrittore del file degli acquirenti di un libro
    private File fdNegotiation;

    private AccountDaoAppFile() {
        try {
            // Istanzia il descrittore del file relativo al file degli account
            fdAccount = new File("csv/" + ACCOUNT_FILE_NAME);
            // Se il file degli accouny non esiste Viene istanziato
            if (!fdAccount.exists() && !fdAccount.createNewFile()) throw new IOException();
            // Istanzia il descrittore del file relativo al file degli acquirenti dei libri
            fdNegotiation = new File("csv/" + NEGOTIATION_FILE_NAME);
            // Se il file degli acquirenti dei libri non esiste Viene istanziato
            if (!fdNegotiation.exists() && !fdNegotiation.createNewFile()) throw new IOException();
        } catch (IOException | NullPointerException e) {
            Printer.error(e);
            System.exit(-1);
        }
    }

    /**
     * Permette di ottenere l'unica istanza di DAO di account che sfrutta il file system
     * @return DAO degli account che utilizza il file system
     */
    public static AccountDaoAppFile getInstance() {
        // Se l'istanza non è presente viene creata
        if(instance == null) instance = new AccountDaoAppFile();
        return instance;
    }

    @Override
    public AccountEntity retrieveAccountByCode(int code) throws AccountNotFoundException {
        // Apre il file CSV
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdAccount)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Controlla che il codice inserito sia uguale a quella del record
                if (Integer.parseInt(tuple[AccountAttributesOrder.CODE.getIndex()]) == code) {
                    // Viene istanziato il tipo relativo all'account
                    AccountTypes type = AccountTypes.getFromString(tuple[AccountAttributesOrder.TYPE.getIndex()]);
                    // Viene istanziato l'entità relativa all'account
                    return new AccountEntity(
                            Integer.parseInt(tuple[AccountAttributesOrder.CODE.getIndex()]),
                            tuple[AccountAttributesOrder.EMAIL.getIndex()],
                            type,
                            tuple[AccountAttributesOrder.NAME.getIndex()],
                            tuple[AccountAttributesOrder.SURNAME.getIndex()]
                    );
                }
            }

            // Nel caso non sia stata trovato alcun record con il codice inserito viene sollevata la relativa eccezione
            throw new AccountNotFoundException();

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        }
        return null;
    }

    @Override
    public List<AccountEntity> retrieveBuyersBySellableBook(SellableBookEntity sellableBook) {


        List<AccountEntity> buyers = new ArrayList<>();

        // Apre il file CSV
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fdNegotiation)))) {

            // Contiene i campi della riga letta
            String[] tuple;

            // Scorre i record all'interno del file
            while ((tuple = csvReader.readNext()) != null) {
                // Viene cercata l'entità relativa all'account e viene aggiunta alla lista
                if(Integer.parseInt(tuple[NegotiationAttributesOrder.BOOK.getIndex()]) == sellableBook.getCode())
                    buyers.add(this.retrieveAccountByCode(Integer.parseInt(tuple[NegotiationAttributesOrder.STUDENT.getIndex()])));
            }

        } catch (IOException | CsvValidationException e) {
            Printer.error(e);
            System.exit(-1);
        } catch (AccountNotFoundException ignored) {
            // Se non c'è nessun account relativo al codice viene ignorato
        }
        // Viene ritornata la lista
        return buyers;
    }
}
