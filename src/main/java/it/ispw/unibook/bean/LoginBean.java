package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.EmailNotValidException;
import it.ispw.unibook.exceptions.FieldNotValidException;

public class LoginBean extends Bean {


    private final String email;
    private final String password;

    public LoginBean(String email, String password) throws FieldNotValidException {
        this.email = email;
        this.password = password;
        validateEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    protected void validateEmail() throws FieldNotValidException {
        try {
            validateField(this.getEmail(), EMAIL_REGEX);
        } catch (FieldNotValidException e) {
            throw new EmailNotValidException(new EmailNotValidException());
        }
    }

}
