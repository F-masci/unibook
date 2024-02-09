package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.EmailNotValidException;
import it.ispw.unibook.exceptions.FieldNotValidException;

public class AccountBean extends Bean {

    private final int code;
    private final String email;
    private final String name;
    private final String surname;

    public AccountBean(int code) throws FieldNotValidException {
        this(code, null, null, null);
    }

    public AccountBean(int code, String email, String name, String surname) throws FieldNotValidException {
        this.code = code;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public int getCode() {
        return code;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    protected void validateEmail() throws FieldNotValidException {
        try {
            if(email != null) validateField(this.getEmail(), EMAIL_REGEX);
        } catch (FieldNotValidException e) {
            throw new EmailNotValidException();
        }
    }

    @Override
    public String toString() {
        return this.getSurname() + " " + this.getName() + " - " + this.getEmail();
    }

}
