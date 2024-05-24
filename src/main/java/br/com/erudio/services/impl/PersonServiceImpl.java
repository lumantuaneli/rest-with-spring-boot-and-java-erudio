package br.com.erudio.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;
import br.com.erudio.util.MathUtils;

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
    
    public Person findById(String pId) {
        logger.info("Finding a person");
        Person person = new Person();
        person.setId(Double.valueOf(MathUtils.convertToDouble(pId, false)).longValue());
        person.setFirstName("Gustav");
        person.setLastName("Mahler");
        person.setAddress("Viena - Austria");
        person.setGender("Male");
        return person;
    }
    
    public Person create(Person pPerson) {
        logger.info("Creating a person");
        pPerson.setId(counter.incrementAndGet());
        return pPerson;
    }
    
    public Person update(Person pPerson) {
        logger.info("Updating a person");
        Person vPerson = findById(Long.valueOf(pPerson.getId()).toString());
        vPerson.setFirstName(pPerson.getFirstName());
        vPerson.setLastName(pPerson.getLastName());
        vPerson.setAddress(pPerson.getAddress());
        vPerson.setGender(pPerson.getGender());
        return vPerson ;
    }

    public Person update(String pId, Person pPerson) {
        logger.info("Updating a person (By its ID)");
        pPerson.setId(Double.valueOf(MathUtils.convertToDouble(pId, false)).longValue());
        return update(pPerson);
    }
    
    public Person delete(String pId) {
        logger.info("Deleting a person");
        Person vPerson = findById(pId);
        //delete
        return vPerson;
    }
    
    private Person mockPerson(int pIndex) {
        Person vPerson = new Person();
        vPerson.setId(counter.incrementAndGet());
        vPerson.setFirstName("Person");
        vPerson.setLastName("#" + pIndex);
        vPerson.setAddress(pIndex + " John Doe st.");
        vPerson.setGender(pIndex % 2 != 0 ? "Male" : "Female");
        return vPerson;
    }
    
    @SuppressWarnings("unused")
    private Person mockPerson(String pId) {
        Person vPerson = new Person();
        long vId = Double.valueOf(MathUtils.convertToDouble(pId, false)).longValue();
        vPerson.setId(vId);
        vPerson.setFirstName("Created");
        vPerson.setLastName("Person #" + pId);
        vPerson.setAddress(vId + " John Doe st.");
        vPerson.setGender(vId % 2 != 0 ? "Male" : "Female");
        return vPerson;
    }
    
}
