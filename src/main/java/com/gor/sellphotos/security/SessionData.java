package com.gor.sellphotos.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionData {

    private Map<String, String> sessionsAndIdentifiant = new HashMap<String, String>();

    /**
     * @param sessionsAndIdentifiant the sessionsAndIdentifiant to set
     */
    public void addASessionAndIdentifiant(String session, String identifiant) {
        this.sessionsAndIdentifiant.put(session, identifiant);
    }

    /**
     * @return the sessionsAndIdentifiant
     */
    public Map<String, String> getSessionsAndIdentifiant() {
        return sessionsAndIdentifiant;
    }

    /**
     * @param sessionsAndIdentifiant the sessionsAndIdentifiant to set
     */
    public void setSessionsAndIdentifiant(Map<String, String> sessionsAndIdentifiant) {
        this.sessionsAndIdentifiant = sessionsAndIdentifiant;
    }

}
