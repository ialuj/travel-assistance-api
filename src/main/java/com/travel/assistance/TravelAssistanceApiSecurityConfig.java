package com.travel.assistance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class TravelAssistanceApiSecurityConfig {
	
	   @Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	      http.cors().and().csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().authorizeRequests() 
	        .requestMatchers("/**").permitAll()
	        .requestMatchers("/api/**").authenticated()
	        .anyRequest().authenticated();
	      return http.build();
	   }
}
