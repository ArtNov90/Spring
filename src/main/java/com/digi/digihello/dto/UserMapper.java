package com.digi.digihello.dto;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.digi.digihello.model.UserAccount;

public class UserMapper {

	public static UserDetails toUserDetails(UserAccount userAccount) {
		return User.builder().username(userAccount.getUsername()).password(userAccount.getPassword()).authorities(
				userAccount.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
				.build();
	}
}
