package com.dionlan.algamoney.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dionlan.algamoney.api.model.AppUser;
import com.dionlan.algamoney.api.repository.AppUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<AppUser> userOptional = appUserRepository.findByEmail(email);
		AppUser appUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(email, appUser.getPassword(), getPermissions(appUser));
	}

	/**
	 * Permissions List create
	 * @param appUser
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getPermissions(AppUser appUser) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		appUser.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}
