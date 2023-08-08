package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.Driver;

import com.rh.vehicle.model.Person;
import com.rh.vehicle.model.PersonClient;
import com.rh.vehicle.repository.DriverRepository;
import com.rh.vehicle.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



/**
 * Service Implementation for managing {@link Driver}.
 */
@Service
@Transactional
public class DriverServiceImpl implements DriverService {
    private final Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);
    private final DriverRepository driverRepository;
    private PersonClient personClient;


    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
        this.personClient = personClient;

    }


    @Override
    public Driver save(Driver driver) {
        log.debug("Request to save Drivers : {}", driver);

        return driverRepository.save(driver);
    }

    @Override
    public Optional<Driver> partialUpdate(Driver driver) {
        log.debug("Request to partially update Driver : {}", driver);
       return driverRepository.findById(driver.getId())
               .map(existingDriver->{
                   if(driver.getNbrmilitaire()!= null) {
                       existingDriver.setNbrmilitaire(driver.getNbrmilitaire());
                   }
                   if(driver.getNpermis()!=null){
                       existingDriver.setNpermis(driver.getNpermis());
                   }
                   if(driver.getMle()!=0){
                       existingDriver.setMle(driver.getMle());
                   }
                   if(driver.getCategoryPermis()!=null){
                       existingDriver.setCategoryPermis(driver.getCategoryPermis());
                   }
                   return existingDriver;
               }).map(driverRepository :: save);

    }

    @Override
    public List<Driver> findAll() {
        log.debug("Request to get all Driver");
        List<Driver> drivers=driverRepository.findAll();
//        drivers.forEach(driver -> {
//            Person person = personClient.findByPersonMle(driver.getMle());
//            driver.setPerson(person);
//        });
        return drivers ;
    }

    @Override
    public Optional<Driver> findOne(String id) {
        log.debug("Request to get Driver : {}", id);
        Optional<Driver> driver = driverRepository.findById(id);
//        if(driver.isPresent()) {
//            Person person = personClient.findByPersonMle(driver.get().getMle());
//            driver.get().setPerson(person);
//        }
        return driver;
    }

    @Override
    public Optional<Driver> findByMle(int mle) {

        log.debug("Request to get Driver : {}", mle);
        Optional<Driver> driver = driverRepository.getDriverByMle(mle);
//        Person person = personClient.findByPersonMle(driver.get().getMle());
//      driver.get().setPerson(person);
        return driver;
    }

    @Override
    public void delete(String id) {
driverRepository.deleteById(id);
    }
}
