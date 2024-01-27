package it.ispw.unibook.entity;

public enum AccountTypes {
    STUDENT, PROFESSOR;

    public static AccountTypes getFromString(String type) {
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
