package com.dionlan.algamoney.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dionlan.algamoney.api.model.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummary {

	private Long id;
	private String description;
	private LocalDate dateDue;
	private LocalDate datePayment;
	private BigDecimal amount;
	private TransactionType transactionType;
	private String category;
	private String person;
}
