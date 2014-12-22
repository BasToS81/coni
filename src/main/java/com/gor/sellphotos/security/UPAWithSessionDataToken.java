package com.gor.sellphotos.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UPAWithSessionDataToken extends UsernamePasswordAuthenticationToken {

    private SecuritySessionData sessionData;

    public UPAWithSessionDataToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public UPAWithSessionDataToken(Object principal, Object credentials, SecuritySessionData sessionData, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.sessionData = sessionData;
    }

    /**
     * @return the sessionData
     */
    public SecuritySessionData getSessionData() {
        return sessionData;
    }

    /**
     * @param sessionData the sessionData to set
     */
    public void setSessionData(SecuritySessionData sessionData) {
        this.sessionData = sessionData;
    }

}
