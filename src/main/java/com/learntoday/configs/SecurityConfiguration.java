package com.learntoday.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain customSecurityConfiguration(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.anyRequest()
													 .authenticated());
		http.csrf(csrf ->csrf.disable());
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	  return new WebMvcConfigurer() {
	    public void addCorsMappings(CorsRegistry registry) {
	      registry.addMapping("/**")
	              .allowedMethods("*")
	              .allowedOrigins("http://localhost:3000");
	  } 
	 };
	}
}
