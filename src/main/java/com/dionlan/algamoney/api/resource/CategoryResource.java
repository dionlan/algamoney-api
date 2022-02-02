package com.dionlan.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.algamoney.api.model.Category;
import com.dionlan.algamoney.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository repository;
	
	@GetMapping
	public ResponseEntity<?> list(){
		List<Category> categories = repository.findAll();
		return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
	}
} 