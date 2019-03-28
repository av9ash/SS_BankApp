package org.asu.cse545.group4.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.asu.cse545.group4.config.SecurityConstants.SIGN_UP_URL;

import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.loginservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
	private EventService eventService;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().antMatcher("/rest/**").authorizeRequests()
                .antMatchers(HttpMethod.POST, "/rest/loginUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),loginService, eventService))
                //.addFilterBefore(new JWTAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic();
                
    	
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("before webc");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        System.out.println("afterwebc");
    }
}
