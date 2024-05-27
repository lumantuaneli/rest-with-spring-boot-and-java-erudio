package br.com.erudio.services;

import java.util.List;

import br.com.erudio.model.Person;

public interface PersonService {

    List<Person> findAll();

    Person findById(Long pId);

    Person create(Person pPerson);

    Person update(Person pPerson);

    Person update(Long pId, Person pPerson);

    Person delete(Long pId);

}
