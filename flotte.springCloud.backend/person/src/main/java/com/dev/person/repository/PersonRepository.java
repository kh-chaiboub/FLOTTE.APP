package com.dev.person.repository;

import com.dev.person.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person,String> {
    public Optional<Person> findByNcin(String ncin);
    public Optional<Person> findByMle(int mle);
}
