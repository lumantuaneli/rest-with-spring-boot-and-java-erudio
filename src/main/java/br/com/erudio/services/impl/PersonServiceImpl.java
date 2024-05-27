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
        return personRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("No record found for the given ID"));
    }
    
    public Person create(Person pPerson) {
        logger.info(String.format("Creating the person \"%s\"", pPerson));
        return personRepository.save(pPerson);
    }
    
    public Person update(Person pPerson) {
        logger.info(String.format("Updating the person \"%s\"", pPerson));
        Person vPerson = findById(pPerson.getId());
        vPerson.setFirstName(pPerson.getFirstName());
        vPerson.setLastName(pPerson.getLastName());
        vPerson.setAddress(pPerson.getAddress());
        vPerson.setGender(pPerson.getGender());
        return personRepository.save(vPerson) ;
    }

    public Person update(Long pId, Person pPerson) {
        logger.info(String.format("Updating the person under the ID \"%s\" with the data of this person: \"%s\"", pId, pPerson));
        pPerson.setId(pId);
        return update(pPerson);
    }
    
    public Person delete(Long pId) {
        logger.info(String.format("Deleting a person with ID \"%s\"", pId));
        Person vPerson = findById(pId);
        personRepository.delete(vPerson);
        return vPerson;
    }
    
}
