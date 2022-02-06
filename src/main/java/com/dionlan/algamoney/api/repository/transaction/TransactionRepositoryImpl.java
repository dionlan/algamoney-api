package com.dionlan.algamoney.api.repository.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.filter.TransactionFilter;
import com.dionlan.algamoney.api.repository.projection.TransactionSummary;

public class TransactionRepositoryImpl implements TransactionRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Transaction> filter(TransactionFilter transactionFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);	
		Root<Transaction> root = criteria.from(Transaction.class);
		
		Predicate[] predicates = createConstraints(transactionFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Transaction> query = manager.createQuery(criteria);
		
		addConstraintsOfPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(transactionFilter));
	}
	
	@Override
	public Page<TransactionSummary> summary(TransactionFilter transactionFilter, Pageable pageable) {	
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TransactionSummary> criteria = builder.createQuery(TransactionSummary.class);
		Root<Transaction> root = criteria.from(Transaction.class);
		criteria.select(builder.construct(TransactionSummary.class, 
						root.get("id"), root.get("description"), root.get("dateDue"), root.get("datePayment"), 
						root.get("amount"), root.get("transactionType"), root.get("category").get("name"), root.get("person").get("name")));
		
		Predicate[] predicates = createConstraints(transactionFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<TransactionSummary> query = manager.createQuery(criteria);
		
		addConstraintsOfPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(transactionFilter));
	}
	
	private Predicate[] createConstraints(TransactionFilter transactionFilter, CriteriaBuilder builder, Root<Transaction> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!ObjectUtils.isEmpty(transactionFilter.getDescription())) {
			predicates.add(builder.like(builder.lower(root.get("description")), "%" + transactionFilter.getDescription().toLowerCase() + "%" ));
		}
		
		if(transactionFilter.getDateDueOf() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dateDue"), transactionFilter.getDateDueOf()));
		}
		
		if(transactionFilter.getDateDueUntil() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dateDue"), transactionFilter.getDateDueUntil()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	public void addConstraintsOfPagination(TypedQuery<?> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRegistersPerPage = pageable.getPageSize();
		int firstRegisterOfPage = currentPage * totalRegistersPerPage;
		
		query.setFirstResult(firstRegisterOfPage);
		query.setMaxResults(totalRegistersPerPage); 
	}
	
	public Long total(TransactionFilter transactionFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Transaction> root = criteria.from(Transaction.class);
		Predicate[] predicates = createConstraints(transactionFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
}
