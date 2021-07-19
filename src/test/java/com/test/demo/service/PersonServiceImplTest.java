package com.test.demo.service;

import com.test.demo.dao.Person;
import com.test.demo.repository.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepo personRepo;

    @InjectMocks
    PersonServiceImpl personService;

    @Test
    void addPerson() {
        Person person = new Person();
        person.setPersonId("1");
        person.setFirstName("testName");

        when(personRepo.save(any())).thenReturn(person);
        Person personCreated = personService.addUpdatePerson(person,false);

        assertEquals(person.getFirstName(), personCreated.getFirstName());
    }
}