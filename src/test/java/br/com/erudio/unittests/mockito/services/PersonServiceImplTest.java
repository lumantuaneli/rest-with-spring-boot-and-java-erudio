package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.logging.Logger;

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
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.impl.PersonServiceImpl;
import br.com.erudio.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(PersonServiceImplTest.class.getName());

    MockPerson input;
    
    @InjectMocks
    private PersonServiceImpl personService;
    
    @Mock
    PersonRepository personRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        fail("Not yet implemented");
    }

    @Test
    void testFindById() {
        long vId = 111L;
        String vExpectedLinks = String.format("</person/v1/%s>;rel=\"self\"", vId);
        String vExpectedFirstName = String.format("First Name Test #%s", vId);
        String vExpectedLastName = String.format("Last Name Test #%s", vId);
        String vExpectedAddress = String.format("Address Test #%s", vId);
        String vExpectedGender = (vId % 2) == 0 ? "Male" : "Female";

        Person vPersonEntity = input.mockEntity(vId);
        
        when(personRepository.findById(vId)).thenReturn(Optional.of(vPersonEntity));
        
        PersonVO vPersonVo = personService.findById(vId);
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
        fail("Not yet implemented");
    }

    @Test
    void testUpdatePersonVO() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdateLongPersonVO() {
        fail("Not yet implemented");
    }

    @Test
    void testDelete() {
        fail("Not yet implemented");
    }

}
