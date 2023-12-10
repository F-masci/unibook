package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.login.EmailNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginBean {


    private final String email;
    private final String password;
    protected String regex = "^(.+)@(.+)$";

    public LoginBean(String email, String password) throws EmailNotValidException {
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

    protected void validateEmail() throws EmailNotValidException {
        Matcher matcher = Pattern.compile(regex).matcher(this.getEmail());
        if(!matcher.matches()) throw new EmailNotValidException();
    }

}
