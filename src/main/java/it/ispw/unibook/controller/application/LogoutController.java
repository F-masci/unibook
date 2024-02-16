package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.Bean;
import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.exceptions.login.SessionException;
import it.ispw.unibook.exceptions.login.SessionNotFoundException;
import it.ispw.unibook.utils.SessionManager;

public class LogoutController {

    /**
     * Permette all'utente di uscire dal sistema
     *
     * @param bean Contiene la sessione dell'utente
     * @throws SessionException Viene sollevata nel caso in cui il codice della sessione non sia valido
     */
    public void logout(Bean bean) throws SessionException {
        try {
            // Rimuove la sessione dell'utente loggato
            AccountEntity loggedUser = SessionManager.getAccountBySessionID(bean.getSessionId());
            SessionManager.removeSessione(loggedUser);
        } catch(SessionNotFoundException e) {
            throw new SessionException(e.getMessage(), e);
        }
    }

}
