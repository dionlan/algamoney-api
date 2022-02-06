package com.dionlan.algamoney.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
	
	public Optional<UserPermission> findByEmail(String email);
	
	public List<UserPermission> findByPermissionsDescription(String authorizationsDescription);
	
}
