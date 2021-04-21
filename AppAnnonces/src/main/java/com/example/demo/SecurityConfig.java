package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder=passwordEncoder();
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("ann1").password(passwordEncoder.encode("123")).roles("ANNONCEUR");
		auth.inMemoryAuthentication().withUser("ann2").password(passwordEncoder.encode("123")).roles("ANNONCEUR");
		auth.inMemoryAuthentication().withUser("et1").password(passwordEncoder.encode("123")).roles("ETUDIANT");
		auth.inMemoryAuthentication().withUser("et1").password(passwordEncoder.encode("123")).roles("ETUDIANT");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest()
					.authenticated()
						.and()
			.formLogin()
				.loginPage("/login")
				.permitAll();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
