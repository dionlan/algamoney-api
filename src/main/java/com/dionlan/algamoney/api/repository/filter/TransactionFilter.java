package com.dionlan.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TransactionFilter {
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDueOf;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDueUntil;

}
