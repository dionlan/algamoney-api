package com.dionlan.algamoney.api.resource;

import java.util.List;

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

import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.TransactionRepository;
import com.dionlan.algamoney.api.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private TransactionService service;
	
	@GetMapping
	public ResponseEntity<List<Transaction>> list(){
		List<Transaction> transactions = repository.findAll();
		
		return !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}")
	public Transaction listById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Transaction> create(@RequestBody @Valid Transaction transaction) {
		
		Transaction transactionSaved = service.save(transaction);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionSaved);
	}
}
