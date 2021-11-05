package com.example.myprofile.services;

import com.example.myprofile.entity.Usersdata;
import com.example.myprofile.principle.UserPrinciple;
import com.example.myprofile.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserService  implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    public Usersdata createUser(Usersdata usersdata){
        return userRepository.save(usersdata);
    }

    public boolean userAlreadyExists(String username){
        System.out.println("already exists");
        if(userRepository.findByUsername(username)!=null){
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usersdata usersdata=userRepository.findByUsername(username);
        //System.out.println("User found:"+usersdata.getUsername());
        if(usersdata==null){
            log.error(username+" notfound");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(usersdata);
    }
    
}
