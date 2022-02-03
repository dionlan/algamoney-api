package com.dionlan.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.algamoney.api.model.Person;
import com.dionlan.algamoney.api.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonResource {

	@Autowired
	private PersonRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Person>> list(){
		List<Person> listPersons = repository.findAll();
		return !listPersons.isEmpty() ? ResponseEntity.ok(repository.findAll()) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
		Person personCreate = repository.save(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(personCreate);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> listById(@Valid @PathVariable Long id) {
		Optional<Person> person = repository.findById(id);
		
		if(person.isPresent()) {
			return ResponseEntity.ok(person.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
