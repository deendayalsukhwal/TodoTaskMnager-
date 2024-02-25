package com.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.todo.security.CustomUserDetailService;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();	
		}
	
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
		.authorizeHttpRequests((authorize)->{
//			authorize.requestMatchers(HttpMethod.POST, "/API/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.PUT,"/API/**").hasARole("ADMIN");
//			authorize.requestMatchers(HttpMethod.DELETE,"/API/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.GET,"/API/**").hasAnyRole("ADMIN","USER");
//			authorize.requestMatchers(HttpMethod.PATCH,"/API/**").hasAnyRole("ADMIN","USER");
	//		authorize.requestMatchers(HttpMethod.GET,"/API/**").permitAll();
			authorize.anyRequest().authenticated();})
			.httpBasic(Customizer.withDefaults());
		
		
		return httpSecurity.build();
	}
	
	//in memory security configuration 
	
//	@Bean
//	UserDetailsService userDetailsService() {
//		
//		UserDetails ravi = User.builder()
//				.username("rajesh")
//				.password(passwordEncoder().encode("ravi"))
//				.roles("USER")
//				.build();
//		
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(ravi, admin);
//	}
	
	@Bean
	public AuthenticationManager  authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	

}
