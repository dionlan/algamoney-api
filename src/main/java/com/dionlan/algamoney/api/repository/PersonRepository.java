package com.dionlan.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionlan.algamoney.api.model.Person;

public interface PersonRepository extends JpaRepository <Person, Long> {

}
