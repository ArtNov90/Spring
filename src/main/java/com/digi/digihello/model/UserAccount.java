package com.digi.digihello.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> authorities = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

}
