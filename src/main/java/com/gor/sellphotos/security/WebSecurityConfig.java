package com.gor.sellphotos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
						"/js/**", "/famille/**", "/public/**").permitAll()
				.anyRequest().authenticated();

		// URL privées et roles associées
		http.authorizeRequests().antMatchers("/ws/famille/**")
				.access("hasRole('ROLE_FAMILLE')").antMatchers("/ws/ecole/**")
				.access("hasRole('ROLE_ECOLE')");

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
		http.logout().permitAll();
	}

	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			/*
			 * On set notre authentication handler customisé
			 */
			auth.authenticationProvider(new AuthenticationProviderImpl());
		}

	}

}