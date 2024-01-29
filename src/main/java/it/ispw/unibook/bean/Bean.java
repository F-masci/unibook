package it.ispw.unibook.bean;

import it.ispw.unibook.exceptions.FieldNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bean {

    /* REGEX */
    protected static final String EMAIL_REGEX = "^(.+)@(.+)$";

    private static int sessionId;

    public static void setSessionId(int sessionId) {
        Bean.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    protected void validateField(String field, String regex) throws FieldNotValidException {
        Matcher matcher = Pattern.compile(regex).matcher(field);
        if(!matcher.matches()) throw new FieldNotValidException();
    }

}
