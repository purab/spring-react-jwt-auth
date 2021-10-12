package com.pkharat.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pkharat.app.services.CustomUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserService customUserService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//in memory authentication
		//auth.inMemoryAuthentication().withUser("purab").password(passwordEncoder().encode("123456")).authorities("ADMIN","USER");
		
		//database authentication
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		
		http.authorizeRequests((request)->request.antMatchers("/api/v1/auth/login").permitAll()
				.anyRequest().authenticated()).httpBasic();
		
		http.formLogin();
		
		//
		http.csrf().disable().headers().frameOptions().disable();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
}
