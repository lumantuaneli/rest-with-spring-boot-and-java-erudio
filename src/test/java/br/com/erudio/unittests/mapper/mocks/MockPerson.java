package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;

public class MockPerson {


    public static final int MOCK_LIST_FIRST_ID = 500;
    public static final int MOCK_LIST_COUNT = 14;

    public Person mockEntity() {
        return mockEntity(0L);
    }
    
    public PersonVO mockVO() {
        return mockVO(0L);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (long i = MOCK_LIST_FIRST_ID; i < MOCK_LIST_FIRST_ID + MOCK_LIST_COUNT; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (long i = 500; i < 514; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    /**
     * Creates a mocked instance of {@link Person}, with the ID field set to <code>number</code>
     * @param number .
     * @return
     */
    public Person mockEntity(Long number) {
        return mockEntity(number, false, false);
    }
    
    /**
     * Creates a mocked instance of {@link Person}, with the ID field set to <code>number</code>
     * @param number .
     * @return
     */
    public Person mockUpdatedEntity(Long number) {
        return mockEntity(number, true, false);
    }
    
    /**
     * Creates a mocked instance of {@link Person}
     * @param number
     * @param pYetToPersist When <code>true</code>, the ID field will be set to null (Simulating an instance created to be persisted). 
     *  When <code>false</code>, the ID field will be set with the <code>number</code> value.
     * @return
     */
    public Person mockEntity(Long number, boolean pUpdated, boolean pYetToPersist) {
        number = number == null ? 0L : number;
        Person person = new Person();
        person.setId(pYetToPersist ? null : number.longValue());
        person.setFirstName(String.format("First Name %s #%s", pUpdated ? "Updated" : "Test", number));
        person.setLastName(String.format("Last Name %s #%s", pUpdated ? "Updated" : "Test", number));
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setAddress(String.format("Address %s #%s", pUpdated ? "Updated" : "Test", number));
        return person;
    }

    /**
     * Creates a mocked instance of {@link PersonVO}, with the ID field set to <code>number</code>
     * @param number
     * @return
     */
    public PersonVO mockVO(Long number) {
        return mockVO(number, false, false);
    }
    
    /**
     * Creates a mocked instance of {@link PersonVO}, with the ID field set to <code>number</code>
     * @param number
     * @return
     */
    public PersonVO mockUpdatedVO(Long number) {
        return mockVO(number, true, false);
    }
    
    /**
     * Creates a mocked instance of {@link PersonVO}
     * @param number
     * @param pYetToPersist When <code>true</code>, the ID field will be set to null (Simulating an instance created to be persisted). 
     * When <code>false</code>, the ID field will be set with the <code>number</code> value.
     * @return
     */
    public PersonVO mockVO(Long number, boolean pUpdated, boolean pYetToPersist) {
        number = number == null ? 0L : number;
        PersonVO person = new PersonVO();
        person.setPersonId(pYetToPersist ? null : number.longValue());
        person.setFirstName(String.format("First Name %s #%s", pUpdated ? "Updated" : "Test", number));
        person.setLastName(String.format("Last Name %s #%s", pUpdated ? "Updated" : "Test", number));
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setAddress(String.format("Address %s #%s", pUpdated ? "Updated" : "Test", number));
        return person;
    }

}
