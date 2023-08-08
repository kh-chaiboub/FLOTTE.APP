package com.dev.person.service.impl;

import com.dev.person.domain.Person;
import com.dev.person.model.RefOrgan;
import com.dev.person.model.RefOrganClient;
import com.dev.person.repository.PersonRepository;
import com.dev.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);
private final PersonRepository personRepository;
    private RefOrganClient refOrganClient;
    public PersonServiceImpl(PersonRepository personRepository, RefOrganClient refOrganClient) {
        this.personRepository = personRepository;
        this.refOrganClient = refOrganClient;

    }


    @Override
    public Person save(Person person) {
        log.debug("REST request to save Person : {}", person);
        if (person.getId() != null) {
            //throw new BadRequestAlertException("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Person result = personRepository.save(person);
        return result;
    }

    @Override
    public Optional<Person> partialUpdate(Person person) {


        return personRepository
                .findById(person.getId())
                .map(
                        existingPersone -> {
                            if (person.getFirstName()!= null) {
                                existingPersone.setFirstName(person.getFirstName());
                            }
                            if (person.getLastName() != null) {
                                existingPersone.setLastName(person.getLastName());
                            }
                            if (person.getDateOfBirth() != null) {
                                existingPersone.setDateOfBirth(person.getDateOfBirth());
                            }
                            if (person.getNcin() != null) {
                                existingPersone.setNcin(person.getNcin());
                            }
                            if (person.getFonction() != null) {
                                existingPersone.setFonction(person.getFonction());
                            }
                            if (person.getGrade() != null) {
                                existingPersone.setGrade(person.getGrade());
                            }
                            if (person.getMle() != 0 ) {
                                existingPersone.setMle(person.getMle());
                            }
                            if (person.getGsm() != null) {
                                existingPersone.setGsm(person.getGsm());
                            }


                            return existingPersone;
                        }

                )
                .map(personRepository::save);

    }

    @Override
    public List<Person> findAll() {
        log.debug("Request to get all Person");
        List<Person> personList = personRepository.findAll();
//
//         personList.forEach(prs -> {
//
//            if(prs.getPrefOrgan()!= null){
//                 String pRefOrgan = prs.getPrefOrgan();
//                System.out.println(pRefOrgan);
//                RefOrgan refOrgan =  refOrganClient.getRefOrgan(pRefOrgan);
//                System.out.println(refOrgan);
//                    prs.setRefOrgan(refOrgan);
//            }
//        });
        return personList;
    }

    @Override
    public Page<Person> findAllWithEagerRelationships(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Person> findOne(String id) {
        return personRepository.findById(id);

    }

    @Override
    public Optional<Person> findByNCin(String nCin) {
        Optional<Person> prs = personRepository.findByNcin(nCin);
//        String pRefOrgan = prs.get().getPrefOrgan();
//        RefOrgan refOrgan =  refOrganClient.getRefOrgan(pRefOrgan);
//      System.out.println(refOrgan);

        return prs;
    }

    @Override
    public Optional<Person> findByMle(int mle) {
        return personRepository.findByMle(mle);
    }

    @Override
    public void delete(String id) {
personRepository.deleteById(id);
    }
}
