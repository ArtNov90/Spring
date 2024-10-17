package com.digi.digihello.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.digi.digihello.dto.UserMapper;
import com.digi.digihello.repository.UserAccountRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
		return username -> UserMapper.toUserDetails(userAccountRepository.findByUsername(username));
	}
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/login").permitAll()  // Accès libre à la page d'accueil et de connexion
                .requestMatchers("/logout", "/list-towns").authenticated()  // Authentification requise pour ces pages
                .requestMatchers("/supprimer/**").hasRole("ADMIN")  // Accès uniquement pour ADMIN
                .anyRequest().authenticated()  // Authentification requise pour toutes les autres requêtes
        )
        .formLogin(Customizer.withDefaults()
        )
        .httpBasic(Customizer.withDefaults());  // Active l'authentification de base HTTP pour les requêtes API
        return http.build();
    }
						
				
	}

