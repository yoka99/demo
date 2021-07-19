package com.test.demo;

import com.test.demo.dao.Address;
import com.test.demo.dao.Person;
import com.test.demo.service.AddressService;
import com.test.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private PersonService personService;
    private AddressService addressService;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        String personAction;

        do{
            System.out.print(getInitialText());
            personAction = scanner.next();
            initialAction(personAction);

        } while (!"0".equals(personAction));
    }

    private void initialAction(String personAction){
        if("0".equals(personAction)){System.out.println("Exiting...");}
        else if("1".equals(personAction)){addUpdatePerson(false);}
        else if("2".equals(personAction)){addUpdatePerson(true);}
        else if("3".equals(personAction)){deleteperson();}
        else if("4".equals(personAction)){addUpdateAddress(false);}
        else if("5".equals(personAction)){addUpdateAddress(true);}
        else if("6".equals(personAction)){deleteAddress();}
        else if("7".equals(personAction)){countNumberOfPersons();}
        else if("8".equals(personAction)){listPersons();}
        else{System.out.println("Wrong input");}
    }

    private static String getInitialText(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Please use one of the options below:").append(System.getProperty("line.separator"))
                .append("1: Add Person").append(System.getProperty("line.separator"))
                .append("2: Edit Person").append(System.getProperty("line.separator"))
                .append("3: Delete Person").append(System.getProperty("line.separator"))
                .append("4: Add Address to Person").append(System.getProperty("line.separator"))
                .append("5: Edit Address").append(System.getProperty("line.separator"))
                .append("6: Delete Address").append(System.getProperty("line.separator"))
                .append("7: Count Number of Persons").append(System.getProperty("line.separator"))
                .append("8: List Persons").append(System.getProperty("line.separator"))
                .append("0: Exit").append(System.getProperty("line.separator"))
                .append("Option: ");
        return stringBuilder.toString();
    }

    private String userInput(String inputMessage){
        System.out.print(inputMessage);
        return scanner.next();
    }

    private void addUpdatePerson(boolean updatePerson){
        Person person = new Person();

        person.setPersonId(userInput("Person ID: "));
        person.setFirstName(userInput("First Name: "));
        person.setLastName(userInput("Last Name: "));

        personService.addUpdatePerson(person, updatePerson);
    }

    private void deleteperson(){
        Person person = new Person();
        person.setPersonId(userInput("Person ID: "));
        personService.deletePerson(person);
    }

    private void addUpdateAddress(boolean updateAddress){
        Address address = new Address();
        Person person = new Person();

        if(!updateAddress){
            person.setPersonId(userInput("Person ID: "));
            address.setPerson(person);
        }

        address.setAddressId(userInput("Address ID: "));
        address.setStreet(userInput("Street: "));
        address.setCity(userInput("City: "));
        address.setState(userInput("State: "));
        address.setPostalCode(userInput("Postal: "));

        addressService.addUpdateAddress(address, updateAddress);
    }

    private void deleteAddress(){
        Address address = new Address();
        address.setAddressId(userInput("Address ID: "));
        addressService.deleteAddress(address);
    }

    private void countNumberOfPersons(){
        Long numberOfPersons = personService.countNumberOfPersons();
        System.out.println("Number of persons: " + numberOfPersons);
    }

    private void listPersons(){
        Iterable<Person> listPersons = personService.listPersons();
        for(Person s : listPersons){
            System.out.println(s.toString());
        }
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
}
