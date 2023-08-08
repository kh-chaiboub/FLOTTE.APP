package com.dev.person.service;

import com.dev.person.domain.Person;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
    Person save(Person person);
    Optional<Person> partialUpdate(Person person);
    List<Person> findAll();
    Page<Person> findAllWithEagerRelationships(Pageable pageable);
    Optional<Person> findOne(String id);
    Optional<Person> findByNCin(String nCin);
    Optional<Person> findByMle(int mle);
    void delete(String id);
}
