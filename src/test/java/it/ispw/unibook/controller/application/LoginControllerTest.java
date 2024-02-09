package it.ispw.unibook.controller.application;

import it.ispw.unibook.bean.LoginBean;
import it.ispw.unibook.exceptions.FieldNotValidException;
import it.ispw.unibook.exceptions.login.EmailNotFoundException;
import it.ispw.unibook.exceptions.login.IncorrectPasswordException;
import it.ispw.unibook.exceptions.login.LoginException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginControllerTest {

    /**
     * Viene testato un esempio di login corretto al sistema
     */
    @Test
    void testLoginSuccessfully() {
        // Viene istanziato il controller applicativo per eseguire il login
        LoginController controller = new LoginController();
        // Viene istanziato il bean contenente le credenziali di accesso al sistema
        LoginBean bean = new LoginBean("studente@students.uniroma2.eu", "studente");
        // Viene eseguito il metodo per l'accesso
        // Se non vengono sollevate eccezioni il login è stato effettuato correttamente
        Assertions.assertDoesNotThrow(() -> controller.login(bean));
    }

    /**
     * Viene testato un esempio di login incorretto al sistema.
     * Il metodo dovrebbe restituire un eccezione che ha come causa l'eccezione relativa
     * all'email non trovata
     */
    @Test
    void testLoginEmailNotFound() {
        try {
            // Viene istanziato il controller applicativo per eseguire il login
            LoginController controller = new LoginController();
            // Viene istanziato il bean contenente le credenziali di accesso al sistema
            LoginBean bean = new LoginBean("emailNonPresente@students.uniroma2.eu", "passwordCasuale");
            // Viene eseguito il metodo per l'accesso
            // Se non vengono sollevate eccezioni il login è stato effettuato correttamente
            controller.login(bean);
            Assertions.fail("Il login viene effettuato correttamente");
        } catch(LoginException e) {
            // Il metodo di login solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura di accesso al sistema

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // l'email non trovata
            Assertions.assertEquals(EmailNotFoundException.class, e.getCause().getClass());
        }
    }

    /**
     * Viene testato un esempio di login incorretto al sistema.
     * Il metodo dovrebbe restituire un eccezione che ha come causa l'eccezione relativa
     * alla password errata
     * @throws FieldNotValidException Viene sollevata se l'email inserita è formattata male
     */
    @Test
    void testLoginIncorrectPassword() throws FieldNotValidException {
        try {
            // Viene istanziato il controller applicativo per eseguire il login
            LoginController controller = new LoginController();
            // Viene istanziato il bean contenente le credenziali di accesso al sistema
            LoginBean bean = new LoginBean("studente@students.uniroma2.eu", "passwordErrata");
            // Viene eseguito il metodo per l'accesso
            // Se non vengono sollevate eccezioni il login è stato effettuato correttamente
            controller.login(bean);
            Assertions.fail("Il login viene effettuato correttamente");
        } catch(LoginException e) {
            // Il metodo di login solleva quest'eccezione nel caso in cui ci sia un errore durante la
            // procedura di accesso al sistema

            // Viene controllata la causa che ha generato l'eccezione
            // Il test fallisce se a scatenare l'eccezione non é stata
            // la password errata
            Assertions.assertEquals(IncorrectPasswordException.class, e.getCause().getClass());
        }
    }

}
