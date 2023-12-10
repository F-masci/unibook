package it.ispw.unibook.entity;

public class AccountEntity {

    private final String email;
    private final int code;

    public AccountEntity(int code, String email) {
        this.code = code;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getCode() {
        return code;
    }

}
