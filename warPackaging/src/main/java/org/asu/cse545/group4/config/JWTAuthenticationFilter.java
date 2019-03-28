package org.asu.cse545.group4.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.asu.cse545.group4.client.utils.UserExclusionStrategy;
import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import static org.asu.cse545.group4.config.SecurityConstants.HEADER_STRING;
import static org.asu.cse545.group4.config.SecurityConstants.SECRET;
import static org.asu.cse545.group4.config.SecurityConstants.EXPIRATION_TIME;
import static org.asu.cse545.group4.config.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	
	
	//private AuthenticationManager authenticationManager;
	
	
	private LoginService loginService;
	
	private EventService eventService;
	
	private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, LoginService loginService, EventService eventService) {
    	//super("/rest/loginUser");
    	super.setFilterProcessesUrl("/rest/loginUser");
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.eventService = eventService;
    }
    
    
      
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{
		System.out.println("inside authfilter");
		
		Authentication auth = null;
		TblUser creds = null;
		try {
			creds = new ObjectMapper().readValue(req.getInputStream(), TblUser.class);
			System.out.println("inside authfilter"+creds.getUsername()+" "+creds.getPassword());
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
			System.out.println("before return authfilter");
			TblUser returnedUser = loginService.loadUserByUserName(creds.getUsername());
			int incorrectAttempts = 0;
			returnedUser.setIncorrectAttempts(0);
			loginService.updateUserForAuth(returnedUser);
		} 
		catch(BadCredentialsException badCredentialsException) {
			// update invalid attempt of user
			System.out.println("invalid login attempt.");
			    	
	    	TblUser returnedUser = loginService.loadUserByUserName(creds.getUsername());
						
			int incorrectAttempts = returnedUser.getIncorrectAttempts() + 1;
			System.out.println("invalidattempt : "+ incorrectAttempts);
			if(incorrectAttempts>=3) {
				returnedUser.setStatus(2);
				returnedUser.setIncorrectAttempts(incorrectAttempts);
    		}else {
    			returnedUser.setIncorrectAttempts(incorrectAttempts);
    		}
			System.out.println(returnedUser.getIncorrectAttempts()+ ": "+ returnedUser.getStatus() );
    		loginService.updateUserForAuth(returnedUser);
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			try
			{
				if(incorrectAttempts >= 3)
				{
					res.getOutputStream().write("User Account Locked".getBytes());
				}
				else
				{
					res.getOutputStream().write("Invalid Credentials".getBytes());
				}
			}
			catch(IOException a)
			{
				throw new RuntimeException(badCredentialsException);
			}
			
		}
		catch( InternalAuthenticationServiceException e)
		{
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			
			try
			{
				
				res.getOutputStream().write("Invalid UserName".getBytes());
				
			}
			catch(IOException a)
			{
				throw new RuntimeException(e);
			}
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		System.out.println("inside succfilter::"+((User) auth.getPrincipal()).getUsername());
		
		TblUser user = new TblUser();
		
		user.setUsername(((User) auth.getPrincipal()).getUsername());
		Map<String, Object> returnedUser = loginService.searchUser(user);
		Gson gson = new GsonBuilder().setExclusionStrategies(new UserExclusionStrategy()).create();
		//Map<String,Object> returnMap = new HashMap<String, Object> ();
		//returnMap
		
		TblEventLog event = new TblEventLog();
		if(returnedUser != null)
		{
			event.setEventName("User Logged In Successfully");
		}
		else
		{
			event.setEventName("User Logged In Failed");
		}
		event.setEventType(1);
		eventService.logEvent(event);
		String json =  gson.toJson(returnedUser);
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		res.getOutputStream().write(json.getBytes());
	}
	
}