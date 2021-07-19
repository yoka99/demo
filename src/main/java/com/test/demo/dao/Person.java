package com.test.demo.dao;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"addressList"})
@Entity
public class Person {

    @Id
    String personId;
    String firstName;
    String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    List<Address> addressList;
}
