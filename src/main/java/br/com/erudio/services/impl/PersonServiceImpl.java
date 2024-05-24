package br.com.erudio.services.impl;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonServiceImpl {
    
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    
    public Person findById(String id) {
        logger.info("Finding a person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gustav");
        person.setLastName("Mahler");
        person.setAddress("Viena - Austria");
        person.setGender("Male");
        return person;
    }
}
