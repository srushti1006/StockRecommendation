package com.citi.stockrecommendation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
// 	Without Database
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() 
//	{
//		List<UserDetails> users=new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("rahul").password("12345").roles("USER").build());
//		return new InMemoryUserDetailsManager(users);
//	}
	
//	With Database
	@Autowired
//	autowire will initialise object of the below class 
	private UserDetailsService userDetailsService;
	@Bean
	public AuthenticationProvider authProvider()
	{
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();//used to communicate with database
		provider.setUserDetailsService(userDetailsService);
//		variation 1 without encryption
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		NoOpPasswordEncoder.getInstance -> store password as it is in database don't apply encrypting technique to it
//		variation 2 with encryption
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	protected void configure(HttpSecurity http)throws Exception{
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll();
	}
}
