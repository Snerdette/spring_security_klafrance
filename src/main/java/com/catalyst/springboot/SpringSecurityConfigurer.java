package com.catalyst.springboot;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		.withUser("user")
		.password("root")
		.authorities("USER");
		
		auth.jdbcAuthentication()
		.dataSource(datasource)
		.passwordEncoder(encoder())
		.usersByUsernameQuery("SELECT username, password, is_active FROM employee WHERE username=?")
		.authoritiesByUsernameQuery("SELECT username, 'USER' FROM employee WHERE username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource datasource;
	
	public void setDatasource(DataSource datasource){
		this.datasource = datasource;
	}
}
