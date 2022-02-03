package com.dionlan.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.TransactionRepository;

/**
 * Business Rules of Controller ResourceTransaction
 * @author dius_
 *
 */
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	public Transaction findById(Long id) { 
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	public Transaction save(Transaction transaction) {
		return repository.save(transaction);
	}
}
