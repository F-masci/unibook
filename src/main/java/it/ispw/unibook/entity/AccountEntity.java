package it.ispw.unibook.entity;

public class AccountEntity {

    private final String email;
    private final int code;
    private final AccountTypes type;

    public AccountEntity(int code, String email, AccountTypes type) {
        this.code = code;
        this.email = email;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public int getCode() {
        return code;
    }

    public AccountTypes getType() { return type; }

}
