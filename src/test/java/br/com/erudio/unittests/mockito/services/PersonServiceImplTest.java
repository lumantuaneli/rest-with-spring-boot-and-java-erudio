package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.impl.PersonServiceImpl;
import br.com.erudio.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    private Logger logger = Logger.getLogger(PersonServiceImplTest.class.getName());

    MockPerson mocker;
    
    @InjectMocks
    private PersonServiceImpl personService;
    
    @Mock
    PersonRepository personRepository;
    
    @BeforeAll
    void setUp() throws Exception {
        logger.setLevel(Level.FINE);
        ConsoleHandler vHandler = new ConsoleHandler();
        vHandler.setLevel(Level.FINE);
        logger.addHandler(vHandler);
    }

    @BeforeEach
    void setUpForTest() throws Exception {
        mocker = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindAll() {
        final String vExpectedLinksTemplate = "</person/v1/%s>;rel=\"self\"";
        final String vExpectedFirstNameTemplate = "First Name Test #%s";
        final String vExpectedLastNameTemplate = "Last Name Test #%s";
        final String vExpectedAddressTemplate = "Address Test #%s";

        List<Person> vPreExistingEntityPersons = mocker.mockEntityList();
        
        when(personRepository.findAll()).thenReturn(vPreExistingEntityPersons);
        
        List<PersonVO> vPersonVos = personService.findAll();
        logger.fine("###Validating findAll operation");
        assertNotNull(vPersonVos);
        assertEquals(14, vPersonVos.size());
        for (Long i = 0L, vId = 500L; i < 14L; i++, vId++) {
            String vExpectedLinks = String.format(vExpectedLinksTemplate, vId);
            String vExpectedFirstName = String.format(vExpectedFirstNameTemplate, vId);
            String vExpectedLastName = String.format(vExpectedLastNameTemplate, vId);
            String vExpectedAddress = String.format(vExpectedAddressTemplate, vId);
            String vExpectedGender = (vId % 2) == 0 ? "Male" : "Female";
            
            PersonVO vPersonVo = vPersonVos.get(i.intValue());
            logger.fine(String.format("...Validating %s", vPersonVo));
            assertNotNull(vPersonVo);
            assertNotNull(vPersonVo.getPersonId());
            assertNotNull(vPersonVo.getLinks());
            assertTrue(vPersonVo.getLinks().toString().contains(vExpectedLinks));
            assertEquals(vExpectedFirstName, vPersonVo.getFirstName());
            assertEquals(vExpectedLastName, vPersonVo.getLastName());
            assertEquals(vExpectedAddress, vPersonVo.getAddress());
            assertEquals(vExpectedGender, vPersonVo.getGender());
        }
    }

    @Test
    void testFindById() {
        long vId = 111L;
        String vExpectedLinks = String.format("</person/v1/%s>;rel=\"self\"", vId);
        String vExpectedFirstName = String.format("First Name Test #%s", vId);
        String vExpectedLastName = String.format("Last Name Test #%s", vId);
        String vExpectedAddress = String.format("Address Test #%s", vId);
        String vExpectedGender = (vId % 2) == 0 ? "Male" : "Female";

        /*
         * Entidade que será retornada à título de mock pelo método personRepository.findById(Long)
         */
        Person vPersonEntity = mocker.mockEntity(vId);
        
        /*
         * QUANDO for invocado "personRepository.findById(vId)"
         *      Nesse ponto ele marca a chamada desse método do personRepository, de forma que a próxima chamada a esse método vá para um proxy, e não para a PersonRepository em si
         * ENTÃO RETORNE "Optional.of(vPersonEntity)"
         *      Nesse ponto, a gente determina para o Mockito o que deve ser retornado pelo proxy do QUANDO
         */ 
        when(personRepository.findById(vId)).thenReturn(Optional.of(vPersonEntity));
        
        PersonVO vPersonVo = personService.findById(vId);
        logger.fine(String.format("###Validating findById operation: %s", vPersonVo));
        assertNotNull(vPersonVo);
        assertNotNull(vPersonVo.getPersonId());
        assertNotNull(vPersonVo.getLinks());
        assertTrue(vPersonVo.getLinks().toString().contains(vExpectedLinks));
        assertEquals(vExpectedFirstName, vPersonVo.getFirstName());
        assertEquals(vExpectedLastName, vPersonVo.getLastName());
        assertEquals(vExpectedAddress, vPersonVo.getAddress());
        assertEquals(vExpectedGender, vPersonVo.getGender());
    }

    @Test
    void testCreate() {
        long vId = 111L;
        String vExpectedLinks = String.format("</person/v1/%s>;rel=\"self\"", vId);
        String vExpectedFirstName = String.format("First Name Test #%s", vId);
        String vExpectedLastName = String.format("Last Name Test #%s", vId);
        String vExpectedAddress = String.format("Address Test #%s", vId);
        String vExpectedGender = (vId % 2) == 0 ? "Male" : "Female";

        /*
         * Entidade que será passada, à título de mock, ao método personRepository.save(Person)
         */
        Person vUnpersistedPersonEntity = mocker.mockEntity(vId, false, true);
        /*
         * Entidade que será retornada, à título de mock, pelo método personRepository.save(Person)
         */
        Person vPersistedPersonEntity = mocker.mockEntity(vId);
        
        when(personRepository.save(vUnpersistedPersonEntity)).thenReturn(vPersistedPersonEntity);

        /*
         * O VO de request deve ser com o ID nulo, para simular a chamada ao recurso POST#/person/v1/, que não exige um ID
         */
        PersonVO vRequestData = mocker.mockVO(vId, false, true);
        PersonVO vPersonVo = personService.create(vRequestData);
        logger.fine(String.format("###Validating create operation: %s", vPersonVo));
        assertNotNull(vPersonVo);
        assertNotNull(vPersonVo.getPersonId());
        assertNotNull(vPersonVo.getLinks());
        assertTrue(vPersonVo.getLinks().toString().contains(vExpectedLinks));
        assertEquals(vExpectedFirstName, vPersonVo.getFirstName());
        assertEquals(vExpectedLastName, vPersonVo.getLastName());
        assertEquals(vExpectedAddress, vPersonVo.getAddress());
        assertEquals(vExpectedGender, vPersonVo.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        logger.fine("###Validating creation with null PersonVO");
        Exception vException = assertThrows(RequiredObjectIsNullException.class, 
            () -> {
                personService.create(null);});
        assertTrue(RequiredObjectIsNullException.MSG_DEFAULT.equals(vException.getMessage()));
    }
    
    @Test
    void testUpdatePersonVO() {
        long vId = 111L;
        String vExpectedLinks = String.format("</person/v1/%s>;rel=\"self\"", vId);
        String vExpectedFirstName = String.format("First Name Updated #%s", vId);
        String vExpectedLastName = String.format("Last Name Updated #%s", vId);
        String vExpectedAddress = String.format("Address Updated #%s", vId);
        String vExpectedGender = (vId % 2) == 0 ? "Male" : "Female";

        /*
         * Entidade que será retornada, à título de mock, pelo método personRepository.save(Person)
         */
        Person vPreExistingPersonEntity = mocker.mockEntity(vId);
        /*
         * Entidade que será passada, à título de mock, ao método personRepository.save(Person), 
         * já com seus dados alterados
         */
        Person vUpdatedPersonEntity = mocker.mockUpdatedEntity(vId);
        /*
         * Entidade que será retornada, à título de mock, do método personRepository.save(Person), 
         * após a submissão da alteração ao BD
         */
        Person vPersistedPersonEntity = mocker.mockUpdatedEntity(vId);
        
        when(personRepository.findById(vId)).thenReturn(Optional.of(vPreExistingPersonEntity));
        when(personRepository.save(vUpdatedPersonEntity)).thenReturn(vPersistedPersonEntity);

        PersonVO vRequestData = mocker.mockUpdatedVO(vId);
        PersonVO vPersonVo = personService.update(vRequestData);
        logger.fine(String.format("###Validating update operation: %s", vPersonVo));
        assertNotNull(vPersonVo);
        assertNotNull(vPersonVo.getPersonId());
        assertNotNull(vPersonVo.getLinks());
        assertTrue(vPersonVo.getLinks().toString().contains(vExpectedLinks));
        assertEquals(vExpectedFirstName, vPersonVo.getFirstName());
        assertEquals(vExpectedLastName, vPersonVo.getLastName());
        assertEquals(vExpectedAddress, vPersonVo.getAddress());
        assertEquals(vExpectedGender, vPersonVo.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        logger.fine("###Validating updating with null PersonVO");
        Exception vException = assertThrows(RequiredObjectIsNullException.class, 
            () -> {
                personService.update(null);});
        assertTrue(RequiredObjectIsNullException.MSG_DEFAULT.equals(vException.getMessage()));
    }
    
    @Test
    void testUpdateLongPersonVO() {
        long vId = 100L;
        String vExpectedLinks = String.format("</person/v1/%s>;rel=\"self\"", vId);
        String vExpectedFirstName = String.format("First Name Updated #%s", vId);
        String vExpectedLastName = String.format("Last Name Updated #%s", vId);
        String vExpectedAddress = String.format("Address Updated #%s", vId);
        String vExpectedGender = (vId % 2) == 0 ? "Male" : "Female";

        /*
         * Entidade que será retornada, à título de mock, pelo método personRepository.save(Person)
         */
        Person vPreExistingPersonEntity = mocker.mockEntity(vId);
        /*
         * Entidade que será passada, à título de mock, ao método personRepository.save(Person), 
         * já com seus dados alterados
         */
        Person vUpdatedPersonEntity = mocker.mockUpdatedEntity(vId);
        /*
         * Entidade que será retornada, à título de mock, do método personRepository.save(Person), 
         * após a submissão da alteração ao BD
         */
        Person vPersistedPersonEntity = mocker.mockUpdatedEntity(vId);
        
        when(personRepository.findById(vId)).thenReturn(Optional.of(vPreExistingPersonEntity));
        when(personRepository.save(vUpdatedPersonEntity)).thenReturn(vPersistedPersonEntity);

        PersonVO vRequestData = mocker.mockVO(vId, true, true);
        PersonVO vPersonVo = personService.update(vId, vRequestData);
        logger.fine(String.format("###Validating update (by ID) operation: %s", vPersonVo));
        assertNotNull(vPersonVo);
        assertNotNull(vPersonVo.getPersonId());
        assertNotNull(vPersonVo.getLinks());
        assertTrue(vPersonVo.getLinks().toString().contains(vExpectedLinks));
        assertEquals(vExpectedFirstName, vPersonVo.getFirstName());
        assertEquals(vExpectedLastName, vPersonVo.getLastName());
        assertEquals(vExpectedAddress, vPersonVo.getAddress());
        assertEquals(vExpectedGender, vPersonVo.getGender());
    }

    @Test
    void testUpdateLongWithNullPerson() {
        logger.fine("###Validating updating (By ID) with null PersonVO");
        Exception vException = assertThrows(RequiredObjectIsNullException.class, 
            () -> {
                personService.update(null, mocker.mockUpdatedVO(155L));});
        assertTrue(RequiredObjectIsNullException.MSG_DEFAULT.equals(vException.getMessage()));
    }
    
    @Test
    void testDelete() {
        long vId = 15L;
        
        /*
         * Entidade que será retornada, à título de mock, pelo método personRepository.save(Person)
         */
        Person vPreExistingPersonEntity = mocker.mockEntity(vId);

        when(personRepository.findById(vId)).thenReturn(Optional.of(vPreExistingPersonEntity));
        
        logger.fine(String.format("###Validating delete operation: %s", vPreExistingPersonEntity));
        personService.delete(vId);
    }

}
