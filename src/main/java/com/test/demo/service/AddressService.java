package com.test.demo.service;

import com.test.demo.dao.Address;

public interface AddressService {

    void addUpdateAddress(Address address, boolean editAddress);
    void deleteAddress(Address address);

}
