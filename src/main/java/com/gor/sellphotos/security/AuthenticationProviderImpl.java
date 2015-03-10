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
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;

import com.gor.sellphotos.dao.Utilisateur;
import com.gor.sellphotos.repository.UtilisateurRepository;
import com.gor.sellphotos.utils.DateUtils;

/**
 * Class which defined the authentication logic
 * 
 * @author pcpl7947
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProviderImpl.class);

    @Autowired
    UtilisateurRepository utilisateurRepository;

    /*
     * (non-Javadoc)
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
            if (utilisateur.getDateLimiteAcces().after(DateUtils.getCurrentDate())) {
                LOGGER.debug("User Date d'acces OK");
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority(utilisateur.getRole().name()));
                LOGGER.debug("User role : {1}", utilisateur.getRole().name());
                return new UPAWithSessionDataToken(identifiant, password, grantedAuths);
            }
            else {
                LOGGER.debug("Date d'acces dépassé");
                throw new NonceExpiredException("Délai d'accès expiré");
            }
        }
        else {
            LOGGER.debug("Password don't match");
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
