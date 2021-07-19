package com.test.demo.service;

import com.test.demo.dao.Address;
import com.test.demo.dao.Person;
import com.test.demo.repository.AddressRepo;
import com.test.demo.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepo addressRepo;
    private PersonRepo personRepo;

    @Override
    public void addUpdateAddress(Address address, boolean editAddress) {
        String addressId = address.getAddressId();
        String personId = null;

        Person person = address.getPerson();
        if(null != person){
            personId = person.getPersonId();
        }

        boolean existsAddress = addressRepo.existsById(addressId);
        boolean existsPerson = personRepo.existsById(personId);

        if(existsAddress && !editAddress){
            System.out.println("\nAddress with ID " + addressId +" already existsAddress\n");
        } else if(!existsAddress && editAddress){
            System.out.println("\nAddress with ID " + addressId +" does not exist\n");
        } else if(!existsPerson){
            System.out.println("\nPerson with ID " + personId +" does not exist\n");
        }else{
            Address save = addressRepo.save(address);
        }
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepo.delete(address);
    }

    @Autowired
    public void setAddressRepo(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Autowired
    public void setPersonRepo(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }
}
