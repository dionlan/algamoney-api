package com.dionlan.algamoney.api.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private String logradoure;
	private String number;
	private String complement;
	private String district;
	private String zipcode;
	private String city;
	private String state;
}
