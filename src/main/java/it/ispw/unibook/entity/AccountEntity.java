package it.ispw.unibook.entity;

import java.util.Objects;

public class AccountEntity {

    private final int code;
    private final AccountTypes type;
    private final String email;
    private final String name;
    private final String surname;

    public AccountEntity(int code, String email, AccountTypes type) {
        this(code, email, type, null, null);
    }

    public AccountEntity(int code, String email, AccountTypes type, String name, String surname) {
        this.code = code;
        this.email = email;
        this.type = type;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public int getCode() {
        return code;
    }

    public AccountTypes getType() { return type; }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntity)) return false;
        return Objects.equals( ((AccountEntity) o).getCode(), this.getCode() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode());
    }

}
