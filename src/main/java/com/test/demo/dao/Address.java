package com.test.demo.dao;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class Address {
    @Id
    String addressId;
    String street;
    String city;
    String state;
    String postalCode;

    @ManyToOne
    @JoinColumn(name="id", updatable = false)
    Person person;
}
