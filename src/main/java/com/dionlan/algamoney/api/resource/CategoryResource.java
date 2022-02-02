package com.dionlan.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) {
		Category categorySaved = repository.save(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> listById(@PathVariable Long id){
		Optional<Category> category = repository.findById(id);
		
		if(category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}
		return ResponseEntity.notFound().build();
	}
} 