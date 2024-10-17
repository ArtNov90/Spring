package com.digi.digihello.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.digi.digihello.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	// Méthode pour rechercher un utilisateur par nom d'utilisateur
	public UserAccount findByUsername(String username);
}
