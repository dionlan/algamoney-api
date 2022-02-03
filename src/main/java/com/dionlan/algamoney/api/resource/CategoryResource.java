package com.dionlan.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.algamoney.api.event.ResourceCreatedEvent;
import com.dionlan.algamoney.api.model.Category;
import com.dionlan.algamoney.api.repository.CategoryRepository;
import com.dionlan.algamoney.api.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Category> list(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Category listById(@PathVariable Long id){
		return service.findCategoryById(id);
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category categorySaved = repository.save(category);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySaved.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}
} 