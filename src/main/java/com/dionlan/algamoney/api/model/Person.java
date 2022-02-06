package com.dionlan.algamoney.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@Embedded
	private Address address;
	
	@NotNull
	private Boolean active;
	
	@JsonIgnoreProperties("person")
	@Valid
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<Contact> contacts;
	
	@JsonIgnore
	@Transient
	public boolean isInactive() {
		return !this.active;
	}
}
