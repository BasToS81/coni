package com.gor.sellphotos.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProviderImpl authenticationProviderImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure");

		// Non géré dans notre application angular... pour l'instant...
		http.csrf().disable();

		// Pas d'acces anonyme
		// http.anonymous().disable();

		// URL publiques
		http.authorizeRequests()
				.antMatchers("/", "/*.html", "/webjars/**", "/css/**",
 "/js/**", "/famille/*.js", "/public/**").permitAll()
				.anyRequest().authenticated();

		// URL privées et roles associées
        http.authorizeRequests().antMatchers("/ws/famille/**")
                        .access("hasRole('ROLE_FAMILLE')").antMatchers("/ws/ecole/**")
                        .access("hasRole('ROLE_ECOLE')").antMatchers("/ws/admin/**")
                        .access("hasRole('ROLE_ADMINISTRATEUR')");


		/*
		 * Utilisation du filter Username & password. Il est normalement activé
		 * lorsqu'on utilise le mécanisme de FormLogin de SpringSecurity qui
		 * permet de rediriger l'utilisateur vers des pages de Login / Echec
		 * configurées. Avec AngularJS, on propose un service REST et c'est
		 * l'appli qui gère ensuite.
		 */
		UsernamePasswordAuthenticationConfigurer configurer = new UsernamePasswordAuthenticationConfigurer(
				"/ws/login");
		http.apply(configurer);

		// Authorize logout
        http.logout().logoutUrl("/ws/logout").addLogoutHandler(new LogoutHandler() {
            
            @Override
            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                try {
                    response.sendRedirect("/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).permitAll();

        /*
         * On set notre authentication handler customisé
         */
        http.authenticationProvider(authenticationProviderImpl);

        /*
         * Que fais-t-on si l'utilisateur n'est pas autorisé ? Dans notre cas, retour 401
         */
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        });
	}


}