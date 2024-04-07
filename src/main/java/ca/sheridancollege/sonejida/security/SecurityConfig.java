package ca.sheridancollege.sonejida.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.AllArgsConstructor;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
private AccessDeniedHandler accessDenied;
private UserDetailsService userDetailsService;

@Bean
public AuthenticationManager authManager(HttpSecurity http,
		PasswordEncoder passwordEncoder) throws Exception {
	AuthenticationManagerBuilder authenticationManagerBuilder = 
			http.getSharedObject(AuthenticationManagerBuilder.class);
	authenticationManagerBuilder.userDetailsService(userDetailsService)
	.passwordEncoder(passwordEncoder);
	return authenticationManagerBuilder.build();
}

@Bean
public BCryptPasswordEncoder passwordEncoder() {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	PasswordEncoderFactories.createDelegatingPasswordEncoder();
	return encoder;
}
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	http.authorizeHttpRequests((authz) -> authz
		.requestMatchers(antMatcher("/add")).hasRole("VENDER")
		//permitAll->Can access without logging in
		//.requestMatchers(antMatcher("/p")).hasRole("USER").hasAnyRole("ROLE1", "ROLE2") 
		.requestMatchers(antMatcher("/edit/**")).hasRole("VENDER")
		.requestMatchers(antMatcher("/delete/**")).hasRole("VENDER")
		.requestMatchers(antMatcher("/view")).hasAnyRole("VENDER","GUEST")
		.requestMatchers(antMatcher("/")).permitAll()
		.requestMatchers(antMatcher("/register")).permitAll()
		.requestMatchers(antMatcher("/h2-console/**")).permitAll()
		.requestMatchers(antMatcher("/Images/**")).permitAll()
		.anyRequest().authenticated()
		)
	
		.formLogin((formLogin) -> formLogin
				.loginPage("/login")
				.failureUrl("/login?failed")
				.defaultSuccessUrl("/", true)
				.permitAll()
	    )
		.logout((logout) -> logout
				.deleteCookies("remove")
				.invalidateHttpSession(false)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout")
				.permitAll()
		)
		.exceptionHandling((exceptionHandling) ->exceptionHandling
				.accessDeniedHandler(accessDenied)
		
		);
		return http.build();
	}
}
