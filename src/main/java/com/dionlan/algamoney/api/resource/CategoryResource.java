package com.dionlan.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')") //privilegio do usuario pesquisar categoria e escopo do app client ler
	public List<Category> list(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')") //privilegio do usuario pesquisar categoria por id and escopo do app client visualizar
	public Category listById(@PathVariable Long id){
		return service.findCategoryById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CATEGORY') and #oauth2.hasScope('write')") //privegio do usuario escrever/salvar categoria e do app client escrever/salvar
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category categorySaved = repository.save(category);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySaved.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}
} 