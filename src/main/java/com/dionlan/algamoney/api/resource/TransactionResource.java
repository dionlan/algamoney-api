package com.dionlan.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.algamoney.api.event.ResourceCreatedEvent;
import com.dionlan.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.TransactionRepository;
import com.dionlan.algamoney.api.repository.filter.TransactionFilter;
import com.dionlan.algamoney.api.repository.projection.TransactionSummary;
import com.dionlan.algamoney.api.service.TransactionService;
import com.dionlan.algamoney.api.service.exception.PersonNonExistentOrInactiveException;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private TransactionService service;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
	public Page<Transaction> search(TransactionFilter transactionFilter, Pageable pageable){
		return repository.filter(transactionFilter, pageable);
	}
	
	@GetMapping(params = "summary")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
	public Page<TransactionSummary> summay(TransactionFilter transactionFilter, Pageable pageable){
		return repository.summary(transactionFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
	public Transaction listById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_TRANSACTION') and #oauth2.hasScope('write')")
	public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction transaction, HttpServletResponse response) {
		Transaction transactionSaved = service.save(transaction);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, transactionSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionSaved);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVE_TRANSACTION') and @oauth2.hasScope('write')")
	public void remove(@PathVariable Long id) {
		service.delete(id);
	}
	
	@ExceptionHandler({ PersonNonExistentOrInactiveException.class })
	public ResponseEntity<Object> handlePersonNonexistentOrInactiveException(PersonNonExistentOrInactiveException ex){
		String userMessage = messageSource.getMessage("person.non-existent-or-inactive", null, LocaleContextHolder.getLocale());
		String devMessage = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMessage, devMessage));
		return ResponseEntity.badRequest().body(erros);
	}
}
