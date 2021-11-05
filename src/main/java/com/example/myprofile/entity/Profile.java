package com.example.myprofile.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
// @Table(name="PROFILE_TBL")
public class Profile {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isCompliant;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
