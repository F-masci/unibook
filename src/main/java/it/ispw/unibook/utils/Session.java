package it.ispw.unibook.utils;

public class Session {

    private static int sessionId;

    protected Session() {}

    public static void setSessionId(int sessionId) {
        Session.sessionId = sessionId;
    }

    public static int getSessionId() {
        return sessionId;
    }
}
