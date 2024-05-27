package br.com.erudio.services.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServiceImpl {
    
    @Autowired
    private PersonRepository personRepository;
    
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    
    public List<Person> findAll() {
        logger.info("Listing all persons");
        return personRepository.findAll();
    }
    
    public Person findById(Long pId) {
        logger.info(String.format("Finding a person with ID \"%s\"", pId));
//        Person person = new Person();
//        person.setId(pId);
//        person.setFirstName("Gustav");
//        person.setLastName("Mahler");
//        person.setAddress("Viena - Austria");
//        person.setGender("Male");
        return personRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("No record found for the given ID"));
    }
    
    public Person create(Person pPerson) {
        logger.info("Creating a person");
        return personRepository.save(pPerson);
    }
    
    public Person update(Person pPerson) {
        logger.info(String.format("Updating the person \"%s\"", pPerson));
        Person vPerson = findById(pPerson.getId());
        vPerson.setFirstName(pPerson.getFirstName());
        vPerson.setLastName(pPerson.getLastName());
        vPerson.setAddress(pPerson.getAddress());
        vPerson.setGender(pPerson.getGender());
        return vPerson ;
    }

    public Person update(Long pId, Person pPerson) {
        logger.info(String.format("Updating the person with the ID \"%s\" with the date of this person: \"%s\"", pId, pPerson));
        pPerson.setId(pId);
        return update(pPerson);
    }
    
    public Person delete(Long pId) {
        logger.info(String.format("Deleting a person with ID \"%s\"", pId));
        Person vPerson = findById(pId);
        personRepository.delete(vPerson);
        return vPerson;
    }
    
//    private Person mockPerson(int pIndex) {
//        Person vPerson = new Person();
//        vPerson.setId(counter.incrementAndGet());
//        vPerson.setFirstName("Person");
//        vPerson.setLastName("#" + pIndex);
//        vPerson.setAddress(pIndex + " John Doe st.");
//        vPerson.setGender(pIndex % 2 != 0 ? "Male" : "Female");
//        return vPerson;
//    }
//    
//    @SuppressWarnings("unused")
//    private Person mockPerson(String pId) {
//        Person vPerson = new Person();
//        long vId = Double.valueOf(MathUtils.convertToDouble(pId, false)).longValue();
//        vPerson.setId(vId);
//        vPerson.setFirstName("Created");
//        vPerson.setLastName("Person #" + pId);
//        vPerson.setAddress(vId + " John Doe st.");
//        vPerson.setGender(vId % 2 != 0 ? "Male" : "Female");
//        return vPerson;
//    }
//    
}
