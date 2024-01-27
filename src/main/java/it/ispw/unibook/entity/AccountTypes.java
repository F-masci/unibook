package it.ispw.unibook.entity;

public enum AccountTypes {
    STUDENT, PROFESSOR;

    static public AccountTypes getFromString(String type) {
        switch (type) {
            case "Professore" -> {
                return PROFESSOR;
            }
            case "Studente" -> {
                return STUDENT;
            }
            default -> {
                return null;
            }
        }
    }

}
