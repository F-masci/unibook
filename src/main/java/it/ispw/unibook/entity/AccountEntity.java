package it.ispw.unibook.entity;

import java.util.Objects;

/**
 * Rappresenta le persone registrate al sistema
 */
public class AccountEntity {

    // Codice dell'account
    private final int code;
    // Tipo di account
    private final AccountTypes type;
    // Email dell'account
    private final String email;
    // Nome della persona a cui fa riferimento l'account
    private final String name;
    // Cognome della persona a cui fa riferimento l'account
    private final String surname;

    /**
     * Costruttore di default dell'entità
     * @param code Codice dell'account
     * @param email Email dell'account
     * @param type Tipo di account
     * @param name Nome della persona a cui fa riferimento l'account
     * @param surname Cognome della persona a cui fa riferimento l'account
     */
    public AccountEntity(int code, String email, AccountTypes type, String name, String surname) {
        this.code = code;
        this.email = email;
        this.type = type;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Costruttore alternativo per l'account che utilizza solo il codice, l'email e il tipo
     * @param code Codice dell'account
     * @param email Email dell'account
     * @param type Tipo di account
     */
    public AccountEntity(int code, String email, AccountTypes type) {
        this(code, email, type, null, null);
    }

    /**
     *
     * @return Email dell'account
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return Codice dell'account
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @return Tipo di account
     */
    public AccountTypes getType() { return type; }

    /**
     *
     * @return Nome della persona a cui fa riferimento l'account
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Cognome della persona a cui fa riferimento l'account
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Due account risultano uguali se hanno lo stesso codice.
     * @param o Oggetto da confrontare
     * @return <i>true</i> se gli oggetti sono uguali.
     *         <i>false</i> se gli oggetti sono diversi.
     */
    @Override
    public boolean equals(Object o) {
        // Se l'oggetto è quello corrente sono uguali
        if (this == o) return true;
        // Se l'oggetto è un'istanza diversa da AccountEntity allora sono diversi
        if (!(o instanceof AccountEntity)) return false;
        // Confronto i codici per capire se sono uguali
        return Objects.equals( ((AccountEntity) o).getCode(), this.getCode() );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode());
    }

}
