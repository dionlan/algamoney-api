package com.dionlan.algamoney.api.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dionlan.algamoney.api.model.Person;
import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.PersonRepository;
import com.dionlan.algamoney.api.repository.TransactionRepository;
import com.dionlan.algamoney.api.service.exception.PersonNonExistentOrInactiveException;

/**
 * Business Rules of Controller ResourceTransaction
 * @author dius_
 *
 */
@Service
public class TransactionService {

	@Autowired
	private PersonRepository service;
	
	@Autowired
	private TransactionRepository repository;
	
	public Transaction findById(Long id) { 
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Transaction save(@Valid Transaction transaction) {
		Person person = service.findById(transaction.getPerson().getId()).get();
		if(person == null || person.isInactive()) {
			throw new PersonNonExistentOrInactiveException();
		}
		return repository.save(transaction);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
