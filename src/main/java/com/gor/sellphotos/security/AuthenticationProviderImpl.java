package com.gor.sellphotos.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Class which defined the authentication logic
 * 
 * @author pcpl7947
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProviderImpl.class);


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.
     * core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Get field values
        final String userNameForm = authentication.getPrincipal().toString();
        final String userPasswordForm = authentication.getCredentials().toString();
        
        if (userNameForm.equals("Famille") && userPasswordForm.equals("1")) {
        	List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_FAMILLE"));
            return new UsernamePasswordAuthenticationToken(userNameForm, userPasswordForm, grantedAuths);
        }
        if (userNameForm.equals("Ecole") && userPasswordForm.equals("2")) {
        	List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ECOLE"));
            return new UsernamePasswordAuthenticationToken(userNameForm, userPasswordForm, grantedAuths);
        }
        
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
