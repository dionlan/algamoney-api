package com.dionlan.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
