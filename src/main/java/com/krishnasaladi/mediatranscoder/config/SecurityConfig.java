package com.krishnasaladi.mediatranscoder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${basicauth.username}")
	private String username;

	@Value("${basicauth.password}")
	private String password;



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeRequests((authorizeRequests) ->
						authorizeRequests
								.requestMatchers("/**").hasRole("USER")
								.requestMatchers("/api/transcode").hasRole("USER")
								.anyRequest().permitAll()
				)
				.httpBasic(withDefaults());
		http.csrf().disable();
		return http. build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User. withDefaultPasswordEncoder()
				.username(username)
				.password(password)
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}