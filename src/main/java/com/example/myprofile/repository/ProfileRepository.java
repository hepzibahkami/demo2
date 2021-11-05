package com.example.myprofile.repository;

import com.example.myprofile.entity.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    // static boolean existingProfile(int empId) {
    //     return true;
    // }
    
}
