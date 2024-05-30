package br.com.erudio.services.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
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
    
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonVO> findAll() {
        logger.info("Listing all persons");
        List<Person> vPersonEntities = personRepository.findAll();
        List<PersonVO> vPersonVos = PersonMapper.toVoList(vPersonEntities);
        vPersonVos.stream()
            .forEach(vPersonVo -> vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel()));
        return vPersonVos;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO findById(Long pId) {
        logger.info(String.format("Finding a person with ID \"%s\"", pId));
        Person vPerson = findEntityById(pId);
        PersonVO vPersonVo = PersonMapper.toVo(vPerson);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(pId)).withSelfRel());
        return vPersonVo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO create(PersonVO pPersonVo) {
        logger.info(String.format("Creating the person \"%s\"", pPersonVo));
        Person vPersonEntity = PersonMapper.toEntity(pPersonVo);
        vPersonEntity = personRepository.save(vPersonEntity);
        PersonVO vPersonVo = PersonMapper.toVo(vPersonEntity);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel());
        return vPersonVo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO update(PersonVO pPersonVo) {
        logger.info(String.format("Updating the person \"%s\"", pPersonVo));
        PersonVO vPersonVo = strictlyUpdate(pPersonVo);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel());
        return vPersonVo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO update(Long pId, PersonVO pPersonVo) {
        logger.info(String.format("Updating the person under the ID \"%s\" with the data of this person: \"%s\"", pId, pPersonVo));
        pPersonVo.setPersonId(pId);
        PersonVO vPersonVo = strictlyUpdate(pPersonVo);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel());
        return vPersonVo;
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
    
    /**
     * Focus on the update trasaction, without generating any HATEOAS links
     * @param pPersonVo
     * @return
     */
    private PersonVO strictlyUpdate(PersonVO pPersonVo) {
        Person vPersonEntity = findEntityById(pPersonVo.getPersonId());
        vPersonEntity.setFirstName(pPersonVo.getFirstName());
        vPersonEntity.setLastName(pPersonVo.getLastName());
        vPersonEntity.setAddress(pPersonVo.getAddress());
        vPersonEntity.setGender(pPersonVo.getGender());
        return PersonMapper.toVo(personRepository.save(vPersonEntity));
    }

}
