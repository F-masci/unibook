package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.Bean;
import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.dao.LoginDao;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.exceptions.login.LoginException;
import it.ispw.unibook.factory.ApplicationDaoFactory;
import it.ispw.unibook.utils.SessionManager;
import org.jetbrains.annotations.NotNull;

public class LoginController {

    /**
     * Permette all'utente di eseguire l'accesso al sistema
     *
     * @param bean Contiene le credenziali per eseguire l'accesso al sistema
     * @throws LoginException Viene impostata la causa con una sottoclasse che specifica il motivo per cui non viene eseguito il login
     */
    public boolean login(@NotNull LoginBean bean) throws LoginException {
        try {
            // Viene istanziato il DAO selezionato nel file di config del sistema
            LoginDao dao = ApplicationDaoFactory.getInstance().getLoginDao();

            // Si effettua il login.
            // In caso di successo verrà creata l'entità relativa all'account loggato.
            // In caso di errore verrà sollevata un'eccezione dal DAO che interromperà il processo di login
            AccountEntity account = dao.login(bean.getEmail(), bean.getPassword());

            // L'utente loggato viene inserito tra le sessioni attive del sistema.
            // Si utilizza il codice dell'utente loggato per identificare la sessione
            // relativa e si imposta il codice della sessione nella classe Bean
            SessionManager.addSession(account);
            Bean.setSessionId(account.getCode());
            return true;
        } catch(EmailNotFoundException | IncorrectPasswordException e) {
            // Viene catturata l'eccezione e viene sollevata un'eccezione generale
            // al chiamante impostando quella catturata come causa
            throw new LoginException(e.getMessage(), e);
        }
    }

}
