package com.dionlan.algamoney.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private String street;
	
	@Column(name = "address_number")
	private String number;
	
	private String complement;
	private String district;
	
	@Column(name = "zipcode")
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;
}
