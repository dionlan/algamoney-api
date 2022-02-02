package com.dionlan.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
