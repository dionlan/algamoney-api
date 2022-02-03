package com.dionlan.algamoney.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Optional<Category> findById(Long id);
	List<Category> findAll();
}
