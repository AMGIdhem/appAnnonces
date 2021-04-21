package com.example.demo;

import javax.sql.DataSource;

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
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		PasswordEncoder passwordEncoder=passwordEncoder();
//		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("ann1").password(passwordEncoder.encode("123")).roles("ANNONCEUR");
//		auth.inMemoryAuthentication().withUser("ann2").password(passwordEncoder.encode("123")).roles("ANNONCEUR");
//		auth.inMemoryAuthentication().withUser("et1").password(passwordEncoder.encode("123")).roles("ETUDIANT");
//		auth.inMemoryAuthentication().withUser("et1").password(passwordEncoder.encode("123")).roles("ETUDIANT");
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, password as credentials, true from user where username=?")
			.authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username=?")
			.rolePrefix("ROLE_");
		
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
