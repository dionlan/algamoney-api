package com.dionlan.algamoney.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.dionlan.algamoney.api.model.AppUser;

public class SystemUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private AppUser appUser;
	
	public SystemUser(AppUser appUser, Collection<? extends GrantedAuthority> authorities) {		
		super(appUser.getEmail(), appUser.getPassword(), authorities);
		this.appUser = appUser;
	}

	public AppUser getAppUser() {
		return appUser;
	}
	

}
