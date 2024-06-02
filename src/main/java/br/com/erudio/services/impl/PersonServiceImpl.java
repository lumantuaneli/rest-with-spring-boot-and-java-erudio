package br.com.erudio.services.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    
    @Autowired
    private PersonRepository personRepository;
    
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
        Person vPersonEntity = findEntityById(pId);
        logger.info(String.format("Person entity found: %s", vPersonEntity));
        PersonVO vPersonVo = PersonMapper.toVo(vPersonEntity);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(pId)).withSelfRel());
        logger.info(String.format("Person VO created: %s", vPersonVo));
        return vPersonVo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO create(PersonVO pPersonVo) {
        logger.info(String.format("Inserting the person \"%s\"", pPersonVo));
        
        if (pPersonVo == null) {
            throw new RequiredObjectIsNullException();
        }
        Person vPersonEntity = PersonMapper.toEntity(pPersonVo);
        vPersonEntity = personRepository.save(vPersonEntity);
        logger.info(String.format("Person entity created: %s", vPersonEntity));
        PersonVO vPersonVo = PersonMapper.toVo(vPersonEntity);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel());
        logger.info(String.format("Person VO created: %s", vPersonVo));
        return vPersonVo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO update(PersonVO pPersonVo) {
        logger.info(String.format("Updating this person's data \"%s\"", pPersonVo));
        if (pPersonVo == null) {
            throw new RequiredObjectIsNullException();
        }
        PersonVO vPersonVo = strictlyUpdate(pPersonVo);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel());
        logger.info(String.format("Person VO updated: %s", vPersonVo));
        return vPersonVo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PersonVO update(Long pId, PersonVO pPersonVo) {
        logger.info(String.format("Updating the person under the ID \"%s\" with the following person data: \"%s\"", pId, pPersonVo));
        if (pId == null || pPersonVo == null) {
            throw new RequiredObjectIsNullException();
        }
        pPersonVo.setPersonId(pId);
        PersonVO vPersonVo = strictlyUpdate(pPersonVo);
        vPersonVo.add(linkTo(methodOn(PersonController.class).findById(vPersonVo.getPersonId())).withSelfRel());
        logger.info(String.format("Person VO updated: %s", vPersonVo));
        return vPersonVo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long pId) {
        logger.info(String.format("Deleting a person with ID \"%s\"", pId));
        Person vPersonEntity = findEntityById(pId);
        personRepository.delete(vPersonEntity);
        logger.info(String.format("Person entity deleted: %s", vPersonEntity));
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
        vPersonEntity = personRepository.save(vPersonEntity);
        logger.info(String.format("Person entity updated: %s", vPersonEntity));
        return PersonMapper.toVo(vPersonEntity);
    }

}
