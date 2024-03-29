package com.dionlan.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.algamoney.api.event.ResourceCreatedEvent;
import com.dionlan.algamoney.api.model.Person;
import com.dionlan.algamoney.api.repository.PersonRepository;
import com.dionlan.algamoney.api.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonResource {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Person> list(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Person listById(@Valid @PathVariable Long id) {
		
		return service.findPersonById(id);
	}
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person personCreate = repository.save(person);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, personCreate.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(personCreate);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
		Person personSaved = service.update(id, person);
		return ResponseEntity.ok(personSaved);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertieAtive(@PathVariable Long id, @RequestBody Boolean active) {
		service.updatePropertieAtive(id, active);
	}
}
