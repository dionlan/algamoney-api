package com.dionlan.algamoney.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.Person;

public interface PersonRepository extends JpaRepository <Person, Long> {

	Optional<Person> findById(Long id);
	List<Person> findAll();
}
