package com.test.demo.service;

import com.test.demo.dao.Person;

public interface PersonService {

    Person addUpdatePerson(Person person, boolean editPerson);
    Long countNumberOfPersons();
    Iterable<Person> listPersons();
    void deletePerson(Person person);

}
