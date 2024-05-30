package br.com.erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0L);
    }
    
    public PersonVO mockVO() {
        return mockVO(0L);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (long i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Long number) {
        Person person = new Person();
        person.setId(number.longValue());
        person.setFirstName("First Name Test #" + number);
        person.setLastName("Last Name Test #" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setAddress("Address Test #" + number);
        return person;
    }

    public PersonVO mockVO(Long number) {
        PersonVO person = new PersonVO();
        person.setPersonId(number.longValue());
        person.setFirstName("First Name Test #" + number);
        person.setLastName("Last Name Test #" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setAddress("Address Test #" + number);
        return person;
    }

}
