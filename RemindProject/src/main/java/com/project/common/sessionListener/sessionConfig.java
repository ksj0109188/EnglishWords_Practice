package com.project.common.sessionListener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class sessionConfig implements HttpSessionListener {
    private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();


    public synchronized static void SessionCheck(String userId) {
        String foundedSession = "";
        for (String key : sessions.keySet()) {
            HttpSession session = sessions.get(key);
            if (session != null && session.getAttribute("userId") != null && session.getAttribute("userId").equals(userId)) {
                foundedSession = key.toString();
            }
        }
        removeOverlapIdSession(foundedSession);
    }

    public static void removeOverlapIdSession(String foundedSession) {
        if(foundedSession!=null && foundedSession.length()>0){
            sessions.get(foundedSession).invalidate();
            sessions.remove(foundedSession);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessions.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = sessions.get(se.getSession().getId());
        if(session !=null){
            sessions.get(se.getSession().getId()).invalidate();
            sessions.remove(se.getSession().getId());
        }
    }
}
