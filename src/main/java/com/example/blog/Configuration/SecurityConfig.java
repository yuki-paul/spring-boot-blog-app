package com.example.blog.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	private UserDetailsService userService;
	
	SecurityConfig(UserDetailsService userDetailsService){
		this.userService = userDetailsService;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration autheConfiguration) throws Exception {
		return autheConfiguration.getAuthenticationManager();
	}
	
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
		http.csrf((csrf)-> csrf.disable())
		.authorizeHttpRequests((authorize)-> 
		//authorize.anyRequest().authenticated())
     	authorize.requestMatchers(HttpMethod.GET,"/post/**").permitAll()
     	.requestMatchers("api/auth/**").permitAll()
		.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
		}
	
//	@Bean
//	public UserDetailsService userdetails() {
//		UserDetails yugesh = User.builder().username("yugesh")
//				           .password(passwordEncoder().encode("Paul@7448Jo"))
//				           .roles("USER")
//				           .build();
//		
//		UserDetails admin = User.builder().username("Jothika")
//		           .password(passwordEncoder().encode("Jothika@30"))
//		           .roles("ADMIN")
//		           .build();
//		
//		return new InMemoryUserDetailsManager(yugesh,admin);	           
//	}

}
