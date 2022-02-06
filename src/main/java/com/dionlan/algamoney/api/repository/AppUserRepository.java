package com.dionlan.algamoney.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	public Optional<AppUser> findByEmail(String email);
	
	public List<AppUser> findByPermissionsDescription(String authorizationsDescription);
	
}
