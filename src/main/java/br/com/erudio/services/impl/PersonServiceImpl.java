package br.com.erudio.services.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private br.com.erudio.mapper.custom.PersonMapper personMapper;
    
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonVO> findAll() {
        logger.info("Listing all persons");
        List<Person> vPersons = personRepository.findAll();
        return PersonMapper.toVoList(vPersons);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO findById(Long pId) {
        logger.info(String.format("Finding a person with ID \"%s\"", pId));
        Person vPerson = findEntityById(pId);
        return PersonMapper.toVo(vPerson);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO create(PersonVO pPersonVo) {
        logger.info(String.format("Creating the person \"%s\"", pPersonVo));
        Person vPerson = PersonMapper.toEntity(pPersonVo);
        vPerson = personRepository.save(vPerson);
        return PersonMapper.toVo(vPerson);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public br.com.erudio.data.vo.v2.PersonVO create(br.com.erudio.data.vo.v2.PersonVO pPersonVo) {
        logger.info(String.format("[V2]Creating the person \"%s\"", pPersonVo));
        Person vPerson = personMapper.toEntity(pPersonVo);
        vPerson = personRepository.save(vPerson);
        return personMapper.toVo(vPerson);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO update(PersonVO pPersonVo) {
        logger.info(String.format("Updating the person \"%s\"", pPersonVo));
        Person vPerson = findEntityById(pPersonVo.getId());
        vPerson.setFirstName(pPersonVo.getFirstName());
        vPerson.setLastName(pPersonVo.getLastName());
        vPerson.setAddress(pPersonVo.getAddress());
        vPerson.setGender(pPersonVo.getGender());
        return PersonMapper.toVo(personRepository.save(vPerson)) ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO update(Long pId, PersonVO pPersonVo) {
        logger.info(String.format("Updating the person under the ID \"%s\" with the data of this person: \"%s\"", pId, pPersonVo));
        pPersonVo.setId(pId);
        return update(pPersonVo);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long pId) {
        logger.info(String.format("Deleting a person with ID \"%s\"", pId));
        Person vPerson = findEntityById(pId);
        personRepository.delete(vPerson);
    }
    
    private Person findEntityById(Long pId) {
        return personRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("No person found for the given ID"));
    }
    
}
