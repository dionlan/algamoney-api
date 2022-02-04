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

import org.apache.commons.lang3.StringUtils;

import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.filter.TransactionFilter;

public class TransactionRepositoryImpl implements TransactionRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Transaction> filter(TransactionFilter transactionFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);	
		Root<Transaction> root = criteria.from(Transaction.class);
		
		Predicate[] predicates = createConstraints(transactionFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Transaction> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	private Predicate[] createConstraints(TransactionFilter transactionFilter, CriteriaBuilder builder, Root<Transaction> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(transactionFilter.getDescription())) {
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
}
