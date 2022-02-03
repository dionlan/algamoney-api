package com.dionlan.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dionlan.algamoney.api.model.Person;
import com.dionlan.algamoney.api.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	public Person update(Long id, Person person) {
		Optional<Person> personSaved = findPersonById(id);
		
		BeanUtils.copyProperties(person, personSaved, "id"); //copia os valoers da pessoa do corpo da requisição para a pessoa salva ignorando o id
		return repository.save(person);
	}

	public void updatePropertieAtive(Long id, Boolean active) {
		Person personSaved = findPersonById(id).get();
		personSaved.setActive(active);
		repository.save(personSaved);
	}
	
	private Optional<Person> findPersonById(Long id) {
		Optional<Person> personSaved = repository.findById(id);
		if(!personSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1); 
		}
		return personSaved;
	}
}
