package com.dionlan.algamoney.api.repository.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.filter.TransactionFilter;
import com.dionlan.algamoney.api.repository.projection.TransactionSummary;

public interface TransactionRepositoryQuery  {

	Page<Transaction> filter(TransactionFilter transactionFilter, Pageable pageable);
	Page<TransactionSummary> filterSummary(TransactionFilter transactionFilter, Pageable pageable);
}
