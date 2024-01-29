package it.ispw.unibook.bean;

public class AccountBean extends Bean {

    private final int code;
    private final String email;
    private final String name;
    private final String surname;

    public AccountBean(int code) {
        this(code, null, null, null);
    }

    public AccountBean(int code, String email, String name, String surname) {
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

    @Override
    public String toString() {
        return this.getSurname() + " " + this.getName() + " - " + this.getEmail();
    }

}
