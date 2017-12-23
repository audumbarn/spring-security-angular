package com.projects.ssa;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SpringSecurityAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAngularApplication.class, args);
	}
	
	 @Configuration
	  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	      http
	        .httpBasic()
	      .and()
	        .authorizeRequests()
	          .antMatchers("/index.html", "/home.html", "/login.html", "/", "/home", "/login").permitAll()
	          .anyRequest().authenticated()
	          .and().csrf()
	          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	    }
	  }
	 
	 @RequestMapping("/resource")
		public Map<String,Object> home() {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("id", UUID.randomUUID().toString());
			model.put("content", "Hello World");
			
			return model;
			
		}
		
		@RequestMapping("/user")
		  public Principal user(Principal user) {
		    return user;
		  }
}
