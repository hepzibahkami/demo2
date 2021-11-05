package com.example.myprofile.repository;

import com.example.myprofile.entity.Usersdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<Usersdata,Long>{
    
    public Usersdata findByUsername(String username);
    
}
