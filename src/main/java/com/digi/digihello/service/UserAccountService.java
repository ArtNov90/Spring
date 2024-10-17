package com.digi.digihello.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digi.digihello.dto.UserMapper;
import com.digi.digihello.model.UserAccount;
import com.digi.digihello.repository.UserAccountRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; // Injecter le PasswordEncoder

	// Méthode pour créer un utilisateur avec des rôles spécifiques
	public void createUser(String username, String password, String... authorities) {
		UserAccount user = new UserAccount();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setAuthorities(Arrays.asList(authorities));
		userAccountRepository.save(user);
	}

	// Initialiser deux utilisateurs lors du démarrage de l'application
	@PostConstruct
	public void init() {
		if (userAccountRepository.findAll().isEmpty()) {
			createUser("user", "password", "ROLE_USER");
			createUser("admin", "password", "ROLE_ADMIN");
		}
	}

	
}
