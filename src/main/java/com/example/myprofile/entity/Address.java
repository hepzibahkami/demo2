package com.example.myprofile.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
// @Table(name="address_table")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String doornumber;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private Long pincode;

    // @OneToOne(mappedBy = "profile")
    // private Profile profile;
    
}
