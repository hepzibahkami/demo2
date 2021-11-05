package com.example.myprofile.controller;

import com.example.myprofile.entity.Profile;
import com.example.myprofile.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NewProfileController {
    
    @Autowired
    private ProfileService profileService;
    public NewProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @GetMapping(value = "/index")
    public String viewHome(Model model,Authentication authentication){
        String ausername=authentication.getName();
        System.out.println();
        model.addAttribute("profiles",profileService.findAllProfiles());
        model.addAttribute("ausername", ausername);
        log.info("index page accessed by user"+ausername);
        return "index";
    }
    @GetMapping(value="/addprofile")
    public String showProfileForm(Model model,Authentication authentication){
        String ausername=authentication.getName();
        Profile profile= new Profile();
        model.addAttribute("profile", profile);
        log.info("add profile page accessed by"+ausername);
        return "add_profile";

    }
    @PostMapping(value = "/createProfile")
    public String createProfile(@ModelAttribute("profile") Profile profile,Authentication authentication){
        String ausername=authentication.getName();
        profileService.saveProfile(profile);
        log.info("new profile added by user"+ausername);
        return "redirect:/index";
    }
    @GetMapping(value="/updateProfileForm/{empId}")
    public String updateProfileForm(@PathVariable int empId,Model model,Authentication authentication){
        Profile profile=profileService.getProfileById(empId);
        String ausername=authentication.getName();
        model.addAttribute("profile", profile);
        log.info("update page accessed by user"+ausername);
        return "update_profile";

    }
    @PostMapping(value="updateProfile/{empId}")
    public String createProfile(@ModelAttribute("profile") Profile profile,@PathVariable int empId,Authentication authentication) {
        //Profile profile=profileService.getProfileById(empId);
        String ausername=authentication.getName();
        profileService.updateProfile(empId, profile);
        log.info("profile Id "+empId+"updated by"+ausername);
        return "redirect:/index";
    }
    @GetMapping(value = "deleteProfile/{empId}")
    public String deleteProfileById(@PathVariable int empId,Authentication authentication){
        //Profile profile=profileService.getProfileById(empId);
        String ausername=authentication.getName();
        profileService.deleteProfile(empId);
        log.info("profile id"+empId+"deleted by"+ausername);
        return "redirect:/index";
    }

}
