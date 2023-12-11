package it.ispw.unibook.bean;

public class Bean {

    private static int sessionId;

    public static void setSessionId(int sessionId) {
        Bean.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }
}
