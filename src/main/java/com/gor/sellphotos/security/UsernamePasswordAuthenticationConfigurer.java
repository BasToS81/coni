package com.gor.sellphotos.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gor.sellphotos.dao.UserDto;

/**
 * http://java.dzone.com/articles/secure-rest-services-using
 * 
 * @author QWBT2550
 *
 */
public class UsernamePasswordAuthenticationConfigurer extends AbstractAuthenticationFilterConfigurer<HttpSecurity,UsernamePasswordAuthenticationConfigurer,UsernamePasswordAuthenticationFilter> {
	
	protected UsernamePasswordAuthenticationConfigurer(String defaultLoginProcessingUrl) {
		super(new UsernamePasswordAuthenticationFilter(), defaultLoginProcessingUrl);
	}

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		// Otherwise filter redirect response instead of leave it to background service
		getAuthenticationFilter().setAuthenticationSuccessHandler(new AuthSuccessHandler());
		getAuthenticationFilter().setAuthenticationFailureHandler(new AuthFailureHandler());
	}
	
	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(
			String loginProcessingUrl) {
		System.out.println("Creating login");
		return new AntPathRequestMatcher(loginProcessingUrl, "POST");
	}
	

	private static class AuthSuccessHandler implements AuthenticationSuccessHandler {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request,
					HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
			
				response.setStatus(HttpServletResponse.SC_OK);

		        UserDto user = new UserDto();
		        user.setName((String) authentication.getPrincipal());
		        for (GrantedAuthority authority : authentication.getAuthorities()) {
		        	user.addRole(authority.getAuthority());
		        }

		        
		        ObjectMapper mapper = new ObjectMapper();
		        
		        PrintWriter writer = response.getWriter();
		        mapper.writeValue(writer, user);
		        writer.flush();
			}
	}
	
	private static class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	    @Override
	    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	            AuthenticationException exception) throws IOException, ServletException {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        PrintWriter writer = response.getWriter();
	        writer.write(exception.getMessage());
	        writer.flush();
	    }
	}

}
