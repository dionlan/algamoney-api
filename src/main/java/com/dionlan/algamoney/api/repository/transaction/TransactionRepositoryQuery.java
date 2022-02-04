package com.dionlan.algamoney.api.repository.transaction;

import java.util.List;

import com.dionlan.algamoney.api.model.Transaction;
import com.dionlan.algamoney.api.repository.filter.TransactionFilter;

public interface TransactionRepositoryQuery  {

	List<Transaction> filter(TransactionFilter transactionFilter);
}
