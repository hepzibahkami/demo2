package com.example.myprofile.controller;

import java.util.List;

//import java.util.List;

import com.example.myprofile.entity.Profile;
import com.example.myprofile.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//@Configuration
//@ComponentScan(basePackages = "com.example.myprofile.repository.ProfileRepository")

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    //private Profile profile;
    
    @PostMapping("/profile")
    public Profile addProfile(@RequestBody Profile profile ){
        return profileService.addProfile(profile);
    }
    
    @GetMapping("/profile")
    public List<Profile> findProfiles(){
        return profileService.findAllProfiles();
    }
    @GetMapping("/profile/{empId}")
    public Profile findProfileById(@PathVariable int empId){
        return profileService.getProfileById(empId);
    }
    @PutMapping("/profile/{empId}")
    public Profile updateprofile(@PathVariable int empId, @RequestBody Profile profile){
        return profileService.updateProfile(empId, profile);
    
    }
    
    @DeleteMapping("/profile/{empId}")
    public String deleteprofile(@PathVariable int empId){
        return profileService.deleteProfile(empId);
    }


   



    

    
    
}
