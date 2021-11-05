package com.example.myprofile.services;

import java.util.List;

import com.example.myprofile.entity.Profile;
import com.example.myprofile.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;



//import com.example.myprofile.repository.ProfileRepository;
//@Configuration
//@ComponentScan(basePackages = "com.example.myprofile.repository.ProfileRepository")
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;
    //post method
    public Profile saveProfile(Profile profile){
        return repository.save(profile);
    }
    //public List<Profile> saveProfiles(List<Profile> profiles){
        //return repository.saveAll(profiles);
    //}
    //public List<Profile> getProfiles(){
        //return repository.findAll();
    //}
    public Profile getProfileById(int empId){
        return repository.findById(empId).orElseThrow();
    }
    public List<Profile> findAllProfiles(){
        return repository.findAll();
    }

    //delete
    public String deleteProfile(int empId){
        boolean exists=repository.existsById(empId);
        if(!exists){
            throw new IllegalStateException("student id "+empId+" does not exist");
        }
        else{
            repository.deleteById(empId);
        return "profile with id "+empId+" deleted"; 
        }    
    }

    //put
    public Profile updateProfile(int empId, Profile profile){
        Profile existingProfile=repository.findById(empId).orElse(null);
        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setPhoneNumber(profile.getPhoneNumber());
        existingProfile.setCompliant(profile.isCompliant());
        existingProfile.setRole(profile.getRole());
        existingProfile.setAddress(profile.getAddress());
        return repository.save(existingProfile);
    
    }
    public Profile addProfile(Profile profile) {
        return repository.save(profile);
    }
    
    
}
