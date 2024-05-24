package br.com.erudio.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonServiceImpl {
    
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    
    public List<Person> findAll() {
        List<Person> vPersons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person vPerson = mockPerson(i);
            vPersons.add(vPerson);
        }
        return vPersons;
    }
    
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

    private Person mockPerson(int pIndex) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person");
        person.setLastName("#" + pIndex);
        person.setAddress(pIndex + " John Doe st.");
        person.setGender(pIndex % 2 != 0 ? "Male" : "Female");
        return person;
    }
    
}
