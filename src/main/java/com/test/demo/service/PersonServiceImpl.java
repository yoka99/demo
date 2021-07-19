package com.test.demo.service;

import com.test.demo.dao.Person;
import com.test.demo.repository.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService{

    private PersonRepo personRepo;

    @Override
    public Person addUpdatePerson(Person person, boolean updatePerson) {
        Person personCreated = null;
        String id = person.getPersonId();
        boolean exists = personRepo.existsById(id);

        if(exists && !updatePerson){
            System.out.println("\nPerson with ID " + id +" already exists\n");
        } else if(!exists && updatePerson){
            System.out.println("\nPerson with ID " + id +" does not exist\n");
        } else{
            personCreated = personRepo.save(person);
        }
        return personCreated;
    }

    @Override
    public Long countNumberOfPersons() {
        return personRepo.count();
    }

    @Override
    public Iterable<Person> listPersons() {
        return personRepo.findAll();
    }

    @Override
    public void deletePerson(Person person) {
        personRepo.delete(person);
    }

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

}
