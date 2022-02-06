package com.dionlan.algamoney.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dionlan.algamoney.api.model.UserPermission;

public class SystemUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private UserPermission userPermission;
	
	public SystemUser(UserPermission userPermission, Collection<? extends GrantedAuthority> authorities) {		
		super(userPermission.getEmail(), userPermission.getPassword(), authorities);
		this.userPermission = userPermission;
	}

	public UserPermission getUserPermission() {
		return userPermission;
	}
	

}
