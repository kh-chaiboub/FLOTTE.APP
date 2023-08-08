package com.dev.person.resource;


import com.dev.person.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dev.person.repository.PersonRepository;
import  com.dev.person.service.PersonService;
import  com.dev.person.domain.Person;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/person")
public class PersonResource {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String ENTITY_NAME = "person";
private static PersonService personService;
private static PersonRepository personRepository;
 public  PersonResource(PersonService personService,PersonRepository personRepository){
     this.personService=personService;
     this.personRepository=personRepository;
 }
 @GetMapping("/persons")
    public List<Person> getAllPersons(@RequestParam(required = false,defaultValue ="false" )boolean eagerload){
     LOGGER.info("REST request to get all Persons");
return personService.findAll();
 }

    @GetMapping("/person/{id}")
     public Optional<Person> findByPersonId(@PathVariable String id){
         LOGGER.info("REST request to get Person : {}", id);
         Optional<Person> person = personService.findOne(id);
         return person;

     }
    @GetMapping("/person/ncin/{ncin}")
    public Optional<Person> findByPersonNcin(@PathVariable String ncin){
        LOGGER.info("REST request to get Person : {}", ncin);
        Optional<Person> person = personService.findByNCin(ncin);
        return person;

    }
    @GetMapping("/person/mle/{mle}")
    public Optional<Person> findByPersonMle(@PathVariable Integer mle){
        LOGGER.info("REST request to get Person : {}", mle);
        Optional<Person> person = personService.findByMle(mle);
        return person;

    }

     @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable(value = "id",required = false) final String id,
            @RequestBody Person person ) throws URISyntaxException{
         LOGGER.info("REST request to update Person : {}, {}", id, person);
         if (person.getId() == null) {
             LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
         }
         if (!Objects.equals(id, person.getId())) {
             LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
         }

         if (!personRepository.existsById(id)) {
             LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
         }

         Person result = personService.save(person);
         return new ResponseEntity(result, OK);

     }

    @PostMapping("/person")
    public ResponseEntity<Person> createAutoService(@RequestBody Person person) throws URISyntaxException {
        LOGGER.info("REST request to save Device : {}", person);
        if (person.getId() != null) {
            LOGGER.info("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Person result = personService.save(person);
        return new ResponseEntity(result, OK);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<HttpResponse> deletePerson(@PathVariable String id) {
        LOGGER.info("REST request to delete Person : {}", id);
        personService.delete(id);
        return response(OK,"PERSONS_DELETED_SUCCESSFULY");

    }
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }




}
