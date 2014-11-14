package com.gor.sellphotos.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.gor.sellphotos.dao.Utilisateur;
import com.gor.sellphotos.repository.UtilisateurRepository;

/**
 * Class which defined the authentication logic
 * 
 * @author pcpl7947
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProviderImpl.class);

    @Autowired
    UtilisateurRepository utilisateurRepository;

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
        final String identifiant = authentication.getPrincipal().toString();
        final String password = authentication.getCredentials().toString();
        
        LOGGER.debug("Trying to log : {}", identifiant);

        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant);
        LOGGER.debug("User found : {}", utilisateur);
        if (utilisateur == null) {
            return null;
        }
        
        if (password.equals(utilisateur.getCodeAcces())) {
            LOGGER.debug("User authentication OK");
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(utilisateur.getTypeUtilisateur().name()));
            return new UsernamePasswordAuthenticationToken(identifiant, password, grantedAuths);
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
