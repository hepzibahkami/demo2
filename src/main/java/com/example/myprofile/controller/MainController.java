package com.example.myprofile.controller;

import com.example.myprofile.entity.Usersdata;
import com.example.myprofile.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class MainController {
    @Autowired
    BCryptPasswordEncoder PasswordEncoder;

    public MainController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/register")
    public String registrationForm(Model model){
        Usersdata usersdata=new Usersdata();
        model.addAttribute("usersdata", usersdata);
        log.info("Registration page accessed");
        return "registration";
    }
    

    @GetMapping(value="/login")
    public String login(){
        log.info("login page accessed");
        return "login";
    }

    @PostMapping(value="/registeruser")
    public String registeruser(@ModelAttribute("usersdata") Usersdata usersdata,Model model){
        if(userService.userAlreadyExists(usersdata.getUsername())){
            log.error("username already exists");
            return "redirect:/register?alreadyExists";
        }
        usersdata.setRole("ROLE_USER");
        model.addAttribute("usersdata", usersdata);
    
        usersdata.setPasswords(PasswordEncoder.encode(usersdata.getPasswords()));
        userService.createUser(usersdata);
        log.info("successfully registered user"+usersdata.getUsername());
        return "redirect:/register?success";
    }
    @GetMapping(value = "/")
    public String redirectToIndex(){
        return "redirect:/index";
    }

}

    
    
    

