package com.example.myprofile.principle;

import java.util.Arrays;
import java.util.Collection;

import com.example.myprofile.entity.Usersdata;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple implements UserDetails{
    private Usersdata user;
    
    public UserPrinciple(Usersdata user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        
        return user.getPasswords();
    }

    @Override
    public String getUsername() {
        
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
       
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return true;
    }
    
}
